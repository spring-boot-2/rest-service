package com.dxc.ddc.springboot.h2.trigger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

import org.h2.tools.TriggerAdapter;

/**
 *
 * @author Huanfeng, cai
 * @since JDK 1.8
 * @see H2 mode MySQL- ON UPDATE CURRENT_TIMESTAMP not supported
 * url: https://github.com/commandos59/h2database/issues/491
 */
public class UpdateDateTrigger extends TriggerAdapter {

	@Override
	public void fire(Connection conn, ResultSet oldRow, ResultSet newRow) throws SQLException {
		newRow.updateTimestamp("UPDATE_DATE", Timestamp.from(Instant.now()));
	}
}
