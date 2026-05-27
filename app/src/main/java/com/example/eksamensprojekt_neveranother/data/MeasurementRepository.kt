package com.example.eksamensprojekt_neveranother.data

import io.github.jan.supabase.postgrest.postgrest

class MeasurementRepository {
    private val client = SupabaseConfig.client

    suspend fun saveMeasurement(measurement: Measurement) {
        client.postgrest["measurements"].insert(measurement)
    }

    suspend fun getMeasurements(): List<Measurement> {
        return client.postgrest["measurements"].select().decodeList<Measurement>()
    }
}
