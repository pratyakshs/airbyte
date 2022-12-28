/*
 * Copyright (c) 2022 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.commons.protocol.objects.impl;

import io.airbyte.commons.protocol.objects.AirbyteMessage;
import io.airbyte.commons.protocol.objects.AirbyteMessageType;
import io.airbyte.commons.protocol.objects.ConnectorSpecification;
import io.airbyte.protocol.models.AirbyteMessage.Type;
import io.airbyte.protocol.models.AirbyteTraceMessage;
import java.util.Map;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class AirbyteMessageAdapter implements AirbyteMessage {

  private final io.airbyte.protocol.models.AirbyteMessage airbyteMessage;

  public AirbyteMessageAdapter(final io.airbyte.protocol.models.AirbyteMessage airbyteMessage) {
    this.airbyteMessage = airbyteMessage;
  }

  @Override
  public AirbyteMessageType getType() {
    return fromProtocolObject(airbyteMessage.getType());
  }

  @Override
  public ConnectorSpecification getSpec() {
    return new ConnectorSpecificationAdapter(airbyteMessage.getSpec());
  }

  @Override
  public AirbyteTraceMessage getTrace() {
    return airbyteMessage.getTrace();
  }

  public static AirbyteMessageType fromProtocolObject(final Type type) {
    return fromProtocolObject.get(type);
  }

  private final static Map<Type, AirbyteMessageType> fromProtocolObject = Map.of(
      Type.RECORD, AirbyteMessageType.RECORD,
      Type.STATE, AirbyteMessageType.STATE,
      Type.LOG, AirbyteMessageType.RECORD,
      Type.CONNECTION_STATUS, AirbyteMessageType.CONNECTION_STATUS,
      Type.CATALOG, AirbyteMessageType.CATALOG,
      Type.TRACE, AirbyteMessageType.TRACE,
      Type.SPEC, AirbyteMessageType.SPEC,
      Type.CONTROL, AirbyteMessageType.CONTROL);

}
