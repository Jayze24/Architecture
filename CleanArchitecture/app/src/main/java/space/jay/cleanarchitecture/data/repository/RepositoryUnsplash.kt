package space.jay.cleanarchitecture.data.repository

import space.jay.cleanarchitecture.base.WrapperResult
import space.jay.cleanarchitecture.data.repository.retrofit.NetworkSchool
import space.jay.cleanarchitecture.data.repository.retrofit.data.DataTopic
import space.jay.cleanarchitecture.data.repository.retrofit.service.ServiceUnsplash
import space.jay.cleanarchitecture.domain.boundary.BoundaryUnsplash
import space.jay.cleanarchitecture.domain.entity.Topic
import javax.inject.Inject

class RepositoryUnsplash @Inject constructor(
    private val networkSchool: NetworkSchool,
    private val serviceUnsplash: ServiceUnsplash,
) : BoundaryUnsplash {

    override suspend fun getListTopic(page: Int): WrapperResult<List<Topic>?> =
        networkSchool.safeApiCall { serviceUnsplash.getListTopic(page) }

}