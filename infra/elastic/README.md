# Elasticsearch indices and sample commands

## Create indices

```bash
curl -X PUT "http://localhost:9200/user-items-v1" -H "Content-Type: application/json" -d @infra/elastic/mappings/user-items-v1.json
curl -X PUT "http://localhost:9200/item-rarity-v1" -H "Content-Type: application/json" -d @infra/elastic/mappings/item-rarity-v1.json
curl -X PUT "http://localhost:9200/user-score-v1" -H "Content-Type: application/json" -d @infra/elastic/mappings/user-score-v1.json
curl -X PUT "http://localhost:9200/counters-v1" -H "Content-Type: application/json" -d @infra/elastic/mappings/counters-v1.json
curl -X PUT "http://localhost:9200/processed-events-v1" -H "Content-Type: application/json" -d @infra/elastic/mappings/processed-events-v1.json
curl -X PUT "http://localhost:9200/user-status-v1" -H "Content-Type: application/json" -d @infra/elastic/mappings/user-status-v1.json
```

## Seed counters

```bash
curl -X PUT "http://localhost:9200/counters-v1/_doc/global" -H "Content-Type: application/json" -d '{"activeUsers":0,"updatedAt":"2026-01-07T12:00:00Z"}'
```

## Produce events with kcat

```bash
kcat -b localhost:9092 -t users-events.v1 -k u1 -P <<EOF
{"eventId":"b82b9b89-f3d9-4a04-9b64-ff67196b9cc0","type":"USER_UPSERT","userId":"u1","status":"ACTIVE","occurredAt":"2026-01-07T12:00:00Z"}
EOF

kcat -b localhost:9092 -t user-items-events.v1 -k "u1|m42" -P <<EOF
{"eventId":"2c0d07fa-c0cc-4f30-b6a6-b50e4ca0c3ef","type":"UPSERT","userId":"u1","itemId":"m42","edition":"STEELBOOK","condition":"GOOD","completeness":"FULL","occurredAt":"2026-01-07T12:00:00Z"}
EOF

kcat -b localhost:9092 -t user-items-events.v1 -k "u1|m42" -P <<EOF
{"eventId":"a94baf54-7fe0-47b4-9c6f-0a75b55a4511","type":"REMOVE","userId":"u1","itemId":"m42","edition":"STEELBOOK","condition":"GOOD","completeness":"FULL","occurredAt":"2026-01-07T12:05:00Z"}
EOF
```