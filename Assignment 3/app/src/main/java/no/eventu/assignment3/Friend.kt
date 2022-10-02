package no.eventu.assignment3


class Friend(name: String, birthday: String) {
    val name: String = name;
    val birthday: String = birthday;


    override fun toString(): String {
        return name + " " + birthday;
    }
}