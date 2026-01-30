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

package uk.gov.hmrc.ui.pages.PurchaserAgent

import org.openqa.selenium.By
import uk.gov.hmrc.ui.pages.BasePage

object PurchaserAgentOverviewPage extends BasePage {

  override def pageUrl: String = "about-the-purchasers-agent/purchasers-agent-overview"

  override def pageTitle: String =
    "Purchaser’s agent overview - About the purchaser’s agent - Stamp Taxes Online - GOV.UK"

  val purchaserAgentChange =
    "a[href ='/stamp-duty-land-tax-filing/about-the-purchasers-agent/purchasers-agent-overview/change-agent/RA001']"

  val purchaserAgentRemove = "a[href = '/stamp-duty-land-tax-filing/about-the-purchasers-agent/purchasers-agent-overview/remove-agent/RA001']"

  def yes: String = "#value"

  def no: String = "#value-no"

  def clickPurchaserAgentChange(): Unit = click(By.cssSelector(purchaserAgentChange))
  
  def clickPurchaserAgentRemove(): Unit = click(By.cssSelector(purchaserAgentRemove))
}
