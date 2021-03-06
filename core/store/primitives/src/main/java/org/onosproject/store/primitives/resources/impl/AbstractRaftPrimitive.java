/*
 * Copyright 2017-present Open Networking Laboratory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.onosproject.store.primitives.resources.impl;

import java.util.Collection;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Function;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import io.atomix.protocols.raft.proxy.RaftProxy;
import org.onosproject.store.service.DistributedPrimitive;

import static com.google.common.base.MoreObjects.toStringHelper;
import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Abstract base class for primitives that interact with Raft replicated state machines via proxy.
 */
public abstract class AbstractRaftPrimitive implements DistributedPrimitive {
    private final Function<RaftProxy.State, Status> mapper = state -> {
        switch (state) {
            case CONNECTED:
                return Status.ACTIVE;
            case SUSPENDED:
                return Status.SUSPENDED;
            case CLOSED:
                return Status.INACTIVE;
            default:
                throw new IllegalStateException("Unknown state " + state);
        }
    };

    protected final RaftProxy proxy;
    private final Set<Consumer<Status>> statusChangeListeners = Sets.newCopyOnWriteArraySet();

    public AbstractRaftPrimitive(RaftProxy proxy) {
        this.proxy = checkNotNull(proxy, "proxy cannot be null");
        proxy.addStateChangeListener(this::onStateChange);
    }

    @Override
    public String name() {
        return proxy.name();
    }

    /**
     * Handles a Raft session state change.
     *
     * @param state the updated Raft session state
     */
    private void onStateChange(RaftProxy.State state) {
        statusChangeListeners.forEach(listener -> listener.accept(mapper.apply(state)));
    }

    @Override
    public void addStatusChangeListener(Consumer<Status> listener) {
        statusChangeListeners.add(listener);
    }

    @Override
    public void removeStatusChangeListener(Consumer<Status> listener) {
        statusChangeListeners.remove(listener);
    }

    @Override
    public Collection<Consumer<Status>> statusChangeListeners() {
        return ImmutableSet.copyOf(statusChangeListeners);
    }

    @Override
    public String toString() {
        return toStringHelper(this)
                .add("proxy", proxy)
                .toString();
    }
}