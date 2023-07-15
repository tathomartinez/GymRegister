package com.tatho.showsize_domain.usercase

import android.content.SharedPreferences
import com.tatho.showsize_domain.model.BodyMeasurements
import com.tatho.showsize_domain.repository.IShowSizeRepository
import javax.inject.Inject

class GetBodyMeasurementsListByUidUseCase @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val repository: IShowSizeRepository
) {
    suspend operator fun invoke(): List<BodyMeasurements> {
        return try {
            val uid = sharedPreferences.getString("Uid", "").toString()
            repository.getBodyMeasurements(uid)
        } catch (e: Exception) {
            emptyList()
        }
    }
}
