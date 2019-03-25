import org.scalajs.dom.raw.Element
import scalatags.JsDom.all._


object Home {

  //workaround for async loading
  val elementDemo = new ElementDemo {
    def title: String = "Home"

    def code: String = ""

    def element: Element = div(h1("scala-js-leaflet Demo")).render

    override def onLoad: () => Unit = () => {}
  }

}
