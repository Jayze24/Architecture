//package space.jay.cleanarchitecture.domain.entity
//
///**
// * 일급 컬렉션
// **/
//class ListClassStudent(
//    private val listStudent: MutableList<EntityStudent> = mutableListOf<EntityStudent>(),
//    private var capacity: Int,
//) {
//
//    fun addStudent(student: EntityStudent) =
//        if (capacity > listStudent.size) {
//            listStudent.add(student)
//            true
//        } else {
//            false
//        }
//
//    fun getListStudent(): List<EntityStudent> = listStudent
//
//    fun setCapacity(capacity: Int): Boolean =
//        if (capacity > listStudent.size) {
//            false
//        } else {
//            this.capacity = capacity
//            true
//        }
//
//}