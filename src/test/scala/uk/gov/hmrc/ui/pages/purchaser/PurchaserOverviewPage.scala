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

package uk.gov.hmrc.ui.pages.purchaser

import org.openqa.selenium.By
import uk.gov.hmrc.ui.pages.BasePage

object PurchaserOverviewPage extends BasePage {

  override def pageUrl: String = "about-the-purchaser/purchaser-overview"

  override def pageTitle: String = "Purchaser overview - About the purchaser - Stamp Taxes Online - GOV.UK"

  val purchaserChange =
    "a[href ='/stamp-duty-land-tax-filing/about-the-purchaser/purchaser-overview/change-purchaser/221110169']"

  val PurchaserRemoveLink: String =
    "a[href= '/stamp-duty-land-tax-filing/about-the-purchaser/purchaser-overview/remove-purchaser/PUR002']"

  def yes: String = "#value"

  def no: String = "#value-no"

  def clickPurchaserChange(): Unit = click(By.cssSelector(purchaserChange))

  def clickRemovePurchaser(): Unit = click(By.cssSelector(PurchaserRemoveLink))

}
