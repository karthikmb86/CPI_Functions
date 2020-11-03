
package com.sap.kr

import groovy.xml.StreamingMarkupBuilder

class GroovyTest1 {
    static stringXML = '<person>'+
            '<firstName>John</firstName><lastName>Doe</lastName><age>25</age>'+
            '</person>'
    static main(args) {

        def people = new XmlSlurper().parse(new File("C:/IntellijFiles/Input1.xml"))
        people.person.findAll { p ->
            p.age.toInteger() > 31
        }.each { p ->
            println "${p.lastName}, ${p.firstName} is ${p.age} years old."
        }

        people.person.findAll { p ->
            p.lastName.toString().startsWith('D')
        }.each { p ->
            println "${p.lastName}, ${p.firstName} have their last name starting from D"
        }

        people.person.each{
            if(it.lastName == 'Smith'){
                println "Row with LastName: ${it.lastName} and FirstName: ${it.firstName} will be removed from xml"
                it.replaceNode{}
            }
        }

        def newPeople = new StreamingMarkupBuilder().bind {mkp.yield people}.toString()

        File file = new File("C:/IntellijFiles/out.xml")
        file.write(newPeople)
    }
}
