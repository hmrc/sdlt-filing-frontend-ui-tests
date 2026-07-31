/*
 * Copyright 2025 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.gov.hmrc.ui.pages.Land

import org.openqa.selenium.By
import uk.gov.hmrc.ui.pages.BasePage

object LandOverview extends BasePage {

  override def pageUrl: String = "about-the-land/land-or-property-overview"

  override def pageTitle: String = "Land or property overview - About the land - Stamp Taxes Online - GOV.UK"

  val yes: String = "#value"

  val no: String = "#value-no"

  val updateBanner: By = By.xpath("//h3[contains(normalize-space(),'You have updated')]")

  val removeBanner: By = By.xpath("//h3[contains(normalize-space(),'You have removed')]")

  val landChange: By =
    By.xpath("(//a[contains(@href, '/change-land/')])[1]")

  val landRemove: By =
    By.xpath("(//a[contains(@href, '/remove-land/')])[1]")

  def clickLandChange(): Unit = click(landChange)

  def clickLandRemove(): Unit = click(landRemove)

  def addTheLandOrProperty(): Unit = click(By.cssSelector(Locators.btnContinue))

}
