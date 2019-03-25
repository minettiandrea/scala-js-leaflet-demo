import scala.scalajs.js.annotation.JSExportTopLevel
import org.scalajs.dom

import scalatags.JsDom.tags
import scalatags.JsDom.all._
import scaladget.bootstrapnative.bsn._
import scaladget.bootstrapnative.bsnsheet
import scaladget.tools._

object Demo {




  @JSExportTopLevel("demo")
  def demo(): Unit = {

    def imports =
      """
      |import ch.wavein.leaflet.{LatLng, Leaflet}
      |import scalatags.JsDom.all._
      |
      """.stripMargin

    val demos = Seq(
      QuickStart(AccessToken.accessToken).elementDemo,
    )

    val tabs = demos.foldLeft(Tabs.tabs()) { (acc, demo) =>
      acc.add(demo.title,
        div(marginLeft := 15, marginTop := 25)(
          h3(demo.title),
          div(row)(
            div(colMD(demo.codeWidth))(pre(code(toClass("scala"))(imports + demo.cleanCode))),
            div(colMD(12 - demo.codeWidth))(demo.element)
          )
        )
      )
    }

    dom.document.body.appendChild(
      div(padding := 20)(
        tabs.build.render(bsnsheet.pills)
      ).render
    )

    demos.foreach(_.onLoad())

    dom.document.body.appendChild(tags.script("hljs.initHighlighting();"))
  }
}