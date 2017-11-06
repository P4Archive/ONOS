/*
 * Copyright 2016-present Open Networking Laboratory
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

package org.onosproject.net;

/**
 * Represents OTU (Optical channel Transport Unit) signal type.
 *
 * <p>
 * See ITU G.709 "Interfaces for the Optical Transport Network (OTN)" and
 * Open Networking Foundation "Optical Transport Protocol Extensions Version 1.0".
 * </p>
 */
public enum OtuSignalType {
    OTU1,
    OTU2,
    OTU3,
    OTU4
}