import java.io.File

fun main() {
    data class Message(val address: String?, val topic: String?, val text: String?, val datetime: String?) {
        fun createRow(str: String?, name: String): String {
            if (str == null){
                return ""
            }
            val res =  str.let {  "<tr><td style=\"border: 1px solid #696969; background-color: #E6E6FA; padding: 5px;\"><b>$name</b></td><td style=\"border: 1px solid #696969; background-color: #F8F8FF; padding: 5px;\">$it</td></tr> \n" }
            return res
        }
        
        fun toHTML(): String {
            var template = "<table style=\"font-family: Arial, sans-serif; font-size: 12pt; color: #000000; padding: 10px; border-collapse: collapse; border: 2px solid #696969; width: 20%;\">"
            template += createRow(address, "address")
            template += createRow(topic, "topic")
            template += createRow(text, "text")
            template += createRow(datetime, "datetime")
            template += "</table>"
            return template
        }
    }

    val message = Message("ekateriiina.korzhova@gmail.com", null, "Hello, world!", "19.10.2023T21.35.42")
    val html = message.toHTML()
    File("message.html").writeText(html)
}