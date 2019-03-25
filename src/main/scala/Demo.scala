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


    val demos:Seq[ElementDemo] = Seq(
      Home.elementDemo,
      QuickStart(AccessToken.accessToken).elementDemo,
      DraggableMarker(AccessToken.accessToken).elementDemo,
    )

    def loadTab(t:Tab) = {
      println("loading Tab")
      demos.find(_.title == t.title).foreach(_.onLoad())
    }

    def tabs = demos.foldLeft(Tabs.tabs()) { (acc, demo) =>
      acc.onActivation(t => loadTab(t)).add(demo.title,
        div(marginLeft := 15, marginTop := 25)(
          h3(demo.title),
          div(row)(
            div(colMD(demo.codeWidth))(pre(code(toClass("scala"))(demo.cleanCode))),
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

    dom.document.body.appendChild(tags.script("hljs.initHighlighting();"))
  }
}