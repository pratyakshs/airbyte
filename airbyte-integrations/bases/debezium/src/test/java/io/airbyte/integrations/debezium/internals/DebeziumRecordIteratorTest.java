/*
 * Copyright (c) 2023 Airbyte, Inc., all rights reserved.
 */

package io.airbyte.integrations.debezium.internals;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import io.airbyte.integrations.debezium.CdcTargetPosition;
import io.debezium.engine.ChangeEvent;
import java.time.Duration;
import java.util.Collections;
import java.util.concurrent.LinkedBlockingQueue;
import org.apache.kafka.connect.source.SourceRecord;
import org.junit.jupiter.api.Test;

public class DebeziumRecordIteratorTest {

  @Test
  public void getHeartbeatPositionTest() {
    final DebeziumRecordIterator debeziumRecordIterator = new DebeziumRecordIterator(mock(LinkedBlockingQueue.class),
        mock(CdcTargetPosition.class),
        () -> false,
        () -> {},
        Duration.ZERO);
    final Long lsn = debeziumRecordIterator.getHeartbeatPosition(new ChangeEvent<String, String>() {

      private final SourceRecord sourceRecord = new SourceRecord(null, Collections.singletonMap("lsn", 358824993496L), null, null, null);

      @Override
      public String key() {
        return null;
      }

      @Override
      public String value() {
        return "{\"ts_ms\":1667616934701}";
      }

      @Override
      public String destination() {
        return null;
      }

      public SourceRecord sourceRecord() {
        return sourceRecord;
      }

    });

    assertEquals(lsn, 358824993496L);
    assertEquals(-1, debeziumRecordIterator.getHeartbeatPosition(null));
  }

}
