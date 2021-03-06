/*
 * Copyright 2018 The Data Transfer Project Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.datatransferproject.security.jwe;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.datatransferproject.api.launcher.ExtensionContext;
import org.datatransferproject.api.launcher.TypeManager;
import org.datatransferproject.spi.transfer.security.AuthDataDecryptService;
import org.datatransferproject.spi.transfer.security.PublicKeySerializer;
import org.datatransferproject.spi.transfer.security.SecurityExtension;

import java.util.Set;

import static java.util.Collections.singleton;

/** Handles auth data transfer between the client UI and server using JWE messages. */
public class JWESecurityExtension implements SecurityExtension {
  private TypeManager typeManager;

  @Override
  public void initialize(ExtensionContext context) {
    typeManager = context.getTypeManager();
  }


  @Override
  public Set<PublicKeySerializer> getPublicKeySerializers() {
    return singleton(new JWEPublicKeySerializer());
  }

  @Override
  public Set<AuthDataDecryptService> getDecryptServices() {
    ObjectMapper objectMapper = typeManager.getMapper();
    return singleton(new JWEAuthDataDecryptService(objectMapper));
  }
}
