package com.priyanka.pagepulse;

public class CacheEntry {


        private AuditResponse response;
        private long timestamp;

        public CacheEntry(AuditResponse response, long timestamp) {
            this.response = response;
            this.timestamp = timestamp;
        }

        public AuditResponse getResponse() {
            return response;
        }

        public long getTimestamp() {
            return timestamp;
        }
    }

