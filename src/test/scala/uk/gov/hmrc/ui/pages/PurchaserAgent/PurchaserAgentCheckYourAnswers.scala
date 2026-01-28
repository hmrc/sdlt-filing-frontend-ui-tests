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

object PurchaserAgentCheckYourAnswers extends BasePage {

  override def pageUrl: String = "about-the-purchasers-agent/check-answers"

  override def pageTitle: String = "Check your answers - About the purchaser’s agent - Stamp Taxes Online - GOV.UK"

  val purchaserAgentNameChange =
    "a[href ='/stamp-duty-land-tax-filing/about-the-purchasers-agent/agent-name/change']"

  val purchaserAgentAddressChange =
    "a[href ='/stamp-duty-land-tax-filing/about-the-purchasers-agent/agent-address-lookup?changeRoute=change']"

  val doYouWantToAddAgentContactDetails =
    "a[href ='/stamp-duty-land-tax-filing/about-the-purchasers-agent/add-agent-contact-details/change']"

  val purchaserAgentContactDetails =
    "a[href ='/stamp-duty-land-tax-filing/about-the-purchasers-agent/enter-agent-contact-details/change']"

  val doYouWantToAddReference =
    "a[href ='/stamp-duty-land-tax-filing/about-the-purchasers-agent/add-agent-reference-number/change']"

  val purchaserAgentReferenceNumber =
    "a[href ='/stamp-duty-land-tax-filing/about-the-purchasers-agent/enter-agent-reference-number/change']"

  val isAgentAuthorizedForCorrespondence =
    "a[href ='/stamp-duty-land-tax-filing/about-the-purchasers-agent/agent-authorised-for-correspondence/change']"

  def clickPurchaserAgentNameChange(): Unit = click(By.cssSelector(purchaserAgentNameChange))

  def clickPurchaserAgentAddressChange(): Unit = click(By.cssSelector(purchaserAgentAddressChange))

  def clickAddAgentContactDetailsChange(): Unit = click(By.cssSelector(doYouWantToAddAgentContactDetails))

  def clickPurchaserAgentContactDetailsChange(): Unit = click(By.cssSelector(purchaserAgentContactDetails))

  def clickDoYouWantToAddReferenceChange(): Unit = click(By.cssSelector(doYouWantToAddReference))

  def clickPurchaserAgentReferenceNumberChange(): Unit = click(By.cssSelector(purchaserAgentReferenceNumber))

  def clickIsAgentAuthorizedForCorrespondenceChange(): Unit = click(By.cssSelector(isAgentAuthorizedForCorrespondence))

}
