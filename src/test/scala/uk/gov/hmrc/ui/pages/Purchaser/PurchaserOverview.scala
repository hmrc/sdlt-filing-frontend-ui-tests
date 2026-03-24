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

package uk.gov.hmrc.ui.pages.Purchaser

import org.openqa.selenium.By
import uk.gov.hmrc.ui.pages.BasePage

object PurchaserOverview extends BasePage {

  override def pageUrl: String = "about-the-purchaser/purchaser-overview"

  override def pageTitle: String = "Purchaser overview - About the purchaser - Stamp Taxes Online - GOV.UK"

  val purchaserChange: By = By.xpath("(//a[contains(@href, '/change-purchaser/')])[1]")

  val PurchaserRemoveLink: By = By.xpath("(//a[contains(@href, '/remove-purchaser/')])[1]")

  val mainPurchaserChangeLink: By = By.xpath("//a[contains(@href, '/change-purchaser-1')]")

  def yes: String = "#value"

  def no: String = "#value-no"

  def clickPurchaserChange(): Unit     = click(purchaserChange)
  def clickMainPurchaserChange(): Unit = click(mainPurchaserChangeLink)
  def clickRemovePurchaser(): Unit     = click(PurchaserRemoveLink)

}
