import org.scalajs.dom.raw.Element

trait ElementDemo {
  def title: String
  def code: String
  def element: Element
  def onLoad:() => Unit

  def cleanCode = {
    if (code.startsWith("{")) code.tail.dropRight(1)
    else code
  }

  def codeWidth: Int = 6

}
