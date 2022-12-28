/*
 * Copyright (c) 2022 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.commons.protocol.objects;

import com.fasterxml.jackson.databind.JsonNode;
import io.airbyte.protocol.models.AdvancedAuth;
import io.airbyte.protocol.models.AuthSpecification;
import java.net.URI;
import java.util.List;

public interface ConnectorSpecification {

  URI getDocumentationUrl();

  URI getChangelogUrl();

  JsonNode getConnectorSpecification();

  Boolean isSupportingIncremental();

  Boolean isSupportingNormalization();

  Boolean isSupportingDBT();

  List<DestinationSyncMode> getSupportedDestinationSyncModes();

  // TODO introduce specific interfaces
  AuthSpecification getAuthSpecification();

  // TODO introduce specific interfaces
  AdvancedAuth getAdvancedAuth();

  // Clients should use this interface rather than the underlying object
  @Deprecated
  io.airbyte.protocol.models.ConnectorSpecification getRaw();

}
