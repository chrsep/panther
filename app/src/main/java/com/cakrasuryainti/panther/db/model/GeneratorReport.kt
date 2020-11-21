package com.cakrasuryainti.panther.db.model

data class GeneratorReport(
    val id: String,
    val customer: String,
    val generatorId: String,
    // TODO: Verify what this is.
    val period: String,
    val runningHours: Int,
    val generatorClean: String,
    val notesAndRecommendation: String
) {
}