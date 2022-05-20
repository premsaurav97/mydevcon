package com.ps.datastreamingapp.debeziumconfig;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.kafka.connect.data.Struct;
import org.apache.kafka.connect.source.SourceRecord;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import io.debezium.data.Envelope.Operation;
import io.debezium.embedded.Connect;
import io.debezium.engine.DebeziumEngine;
import io.debezium.engine.RecordChangeEvent;
import io.debezium.engine.format.ChangeEventFormat;

@Component
public class CustomDebeziumConfig {

	// Define the configuration for the Debezium Engine with MySQL connector...
	private DebeziumEngine<RecordChangeEvent<SourceRecord>> debeziumEngine;

	private final Executor executor = Executors.newSingleThreadExecutor();

	@PostConstruct
	private void start() {
		DebeziumListener();
		this.executor.execute(debeziumEngine);
	}

	@PreDestroy
	private void stop() throws IOException {
		if (this.debeziumEngine != null) {
			this.debeziumEngine.close();
		}
	}

	private void handleChangeEvent(RecordChangeEvent<SourceRecord> sourceRecordRecordChangeEvent) {
		SourceRecord sourceRecord = sourceRecordRecordChangeEvent.record();
		Struct sourceRecordChangeValue = (Struct) sourceRecord.value();
		System.out.println(sourceRecord);
		if (sourceRecordChangeValue != null) {
			Operation operation = Operation.forCode((String) sourceRecordChangeValue.get("op"));

			if (operation != Operation.READ) {
				String record = operation == Operation.DELETE ? "before" : "after";
				Struct struct = (Struct) sourceRecordChangeValue.get(record);
//				System.out.println(struct.schema());
//				System.out.println(struct);
//				System.out.println(struct.schema().fields());
//				System.out.println(struct.toString());
				List<Pair<String, Object>> payload = struct.schema().fields().stream()
						.map(org.apache.kafka.connect.data.Field::name)
						.filter(fieldName -> struct.get(fieldName) != null)
						.map(fieldName -> Pair.of(fieldName, struct.get(fieldName))).collect(Collectors.toList());
				System.out.println(payload);

//	            this.studentService.replicateData(payload, operation);
			}
		}
	}

	public void DebeziumListener() {

		this.debeziumEngine = DebeziumEngine.create(ChangeEventFormat.of(Connect.class)).using(config())
				.notifying(this::handleChangeEvent).build();

//	    this.customerService = customerService;
	}

	public Properties config() {
		final Properties props = new Properties();
		props.setProperty("name", "engine");
		props.setProperty("offset.storage", "org.apache.kafka.connect.storage.FileOffsetBackingStore");
		props.setProperty("offset.storage.file.filename", "c://kafka//test//offsets.dat");
		props.setProperty("offset.flush.interval.ms", "60000");
		/* begin connector properties */
		props.setProperty("connector.class", "io.debezium.connector.sqlserver.SqlServerConnector");
		props.setProperty("database.hostname", "4057P13");
		props.setProperty("database.port", "1433");
		props.setProperty("database.user", "work");
		props.setProperty("database.password", "12345");
		props.setProperty("database.dbname", "mydb");
		props.setProperty("database.server.id", "5908");
		props.setProperty("database.server.name", "4057P13");
		props.setProperty("database.whitelist", "mydb");
		props.setProperty("database.history.kafka.bootstrap.servers", "localhost:9092");
		props.setProperty("database.history.kafka.topic", "cdc.student");
		props.setProperty("database.history", "io.debezium.relational.history.KafkaDatabaseHistory");
//		props.setProperty("database.history.file.filename", "c://kafka//test//dbhistory.dat");

		// Create the engine with this configuration ...
//		try (DebeziumEngine<ChangeEvent<String, String>> engine = DebeziumEngine.create(Json.class).using(props)
//				.notifying(record -> {
//					System.out.println(System.currentTimeMillis()+" "+ record);
//				}).build()) {
//			// Run the engine asynchronously ...
//			ExecutorService executor = Executors.newSingleThreadExecutor();
//			executor.execute(engine);

		// Do something else or wait for a signal or an event
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return props;

	}
}
