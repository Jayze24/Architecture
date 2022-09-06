package space.jay.cleanarchitecture.domain.boundary

import space.jay.cleanarchitecture.base.WrapperResult
import space.jay.cleanarchitecture.domain.entity.Topic

interface BoundaryUnsplash {

    suspend fun getListTopic(page: Int): WrapperResult<List<Topic>?>

}