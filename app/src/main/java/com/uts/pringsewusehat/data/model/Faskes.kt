package com.uts.pringsewusehat.data.model

import java.io.Serializable

/**
 * Data class representing a health facility (Fasilitas Kesehatan) in Pringsewu.
 * Implements Serializable to enable passing objects between Activities via Intent putExtra.
 */
data class Faskes(
    val id: Int,
    val name: String,
    val category: String, // "Rumah Sakit", "Puskesmas", "Klinik"
    val address: String,
    val phone: String,
    val operationalHours: String,
    val doctorSchedule: String,
    val facilities: String,
    val description: String,
    val rating: Double
) : Serializable
