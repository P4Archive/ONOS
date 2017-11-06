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

package org.onosproject.net.pi.model;

import com.google.common.annotations.Beta;

import java.util.List;
import java.util.Optional;

/**
 * Model of a header type in a protocol-independent pipeline.
 */
@Beta
public interface PiHeaderTypeModel {

    /**
     * Returns the name of this header type.
     *
     * @return name
     */
    String name();

    /**
     * Returns the field type model defined by the given name, if present.
     *
     * @param fieldName field name
     * @return optional field type model
     */
    Optional<PiHeaderFieldTypeModel> field(String fieldName);

    /**
     * Returns a list of field type models for this header type, ordered according to the same
     * order of header fields as defined in the pipeline model.
     *
     * @return list of field type models
     */
    List<PiHeaderFieldTypeModel> fields();
}
