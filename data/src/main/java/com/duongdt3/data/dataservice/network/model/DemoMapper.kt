package com.duongdt3.data.dataservice.network.model

import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import kotlin.reflect.KClass
import kotlin.reflect.KParameter
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.primaryConstructor

data class PersonForm(
    val firstName: String,
    val lastName: String,
    val age: Int,
    // maybe many fields exist here like address, card number, etc.
    val tel: String
)

// maps to ...
data class PersonRecord(
    val name: String, // "${firstName} ${lastName}"
    val age: Int, // copy of age
    // maybe many fields exist here like address, card number, etc.
    val tel: String // copy of tel
)


fun PersonForm.toPersonRecordManual() = PersonRecord(
    name = "$firstName $lastName",
    age = age,
    tel = tel
)


fun PersonForm.toPersonRecordReflection() = with(PersonRecord::class.primaryConstructor) {
    val propertiesByName = PersonForm::class.memberProperties.associateBy { it.name }
    this?.callBy(args = parameters.associate { parameter ->
        parameter to when (parameter.name) {
            //custom field name map
            "name" -> "$firstName $lastName"
            //the same field name
            else -> propertiesByName[parameter.name]?.get(this@toPersonRecordReflection)
        }
    })
}


open class Transformer<in T : Any, out R : Any>
protected constructor(inClass: KClass<T>, outClass: KClass<R>) {
    private val outConstructor = outClass.primaryConstructor!!
    private val inPropertiesByName by lazy {
        inClass.memberProperties.associateBy { it.name }
    }

    fun transform(data: T): R = with(outConstructor) {
        callBy(parameters.associate { parameter ->
            parameter to argFor(parameter, data)
        })
    }

    open fun argFor(parameter: KParameter, data: T): Any? {
        return inPropertiesByName[parameter.name]?.get(data)
    }
}

val personFormToPersonRecordTransformer = object
    : Transformer<PersonForm, PersonRecord>(PersonForm::class, PersonRecord::class) {
    override fun argFor(parameter: KParameter, data: PersonForm): Any? {
        return when (parameter.name) {
            "name" -> with(data) { "$firstName $lastName" }
            else -> super.argFor(parameter, data)
        }
    }
}

fun PersonForm.toPersonRecordCachedReflection() = personFormToPersonRecordTransformer.transform(this)


data class Person(var firstName: String?, var lastName: String?, var phoneNumber: String?) {
    // Necessary for MapStruct
    constructor() : this(null, null, null)
}

data class PersonDto(var firstName: String?, var lastName: String?, var phone: String?) {
    // Necessary for MapStruct
    constructor() : this(null, null, null)
}

@Mapper
interface PersonConverter {

    @Mapping(source = "phoneNumber", target = "phone")
    fun convertToDto(person: Person) : PersonDto

    @InheritInverseConfiguration
    fun convertToModel(personDto: PersonDto) : Person

}

class Main {

    fun demoMapStruct() {
        val converter = PersonConverterImpl()
        val person = Person("Samuel", "Jackson", "0123 334466")


        val personDto = converter.convertToDto(person)
        val personModel = converter.convertToModel(personDto)
    }

}