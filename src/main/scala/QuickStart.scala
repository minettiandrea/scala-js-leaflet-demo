import ch.wavein.leaflet.{LatLng, Leaflet, TileLayerOptions}
import scalatags.JsDom.all._
import org.scalajs.dom.raw.Element

case class QuickStart(accessToken:String) {


  val sc = sourcecode.Text {

    val mapDiv = div(height := 300).render

    val onload:() => Unit = () => {

      val map = Leaflet.map(mapDiv).setView(LatLng(51.505, -0.09), 13)


      Leaflet.tileLayer(
        s"https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token=$accessToken",
        new TileLayerOptions {
          override val id = "mapbox.streets"
        }
      ).addTo(map)

    }


    (mapDiv,onload)

  }

  val elementDemo = new ElementDemo {
    def title: String = "QuickStart"

    def code: String = sc.source

    def element: Element = sc.value._1

    override def onLoad: () => Unit = sc.value._2
  }

}

