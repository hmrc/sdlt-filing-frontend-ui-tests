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

package uk.gov.hmrc.ui.pages.VendorAgent

import org.openqa.selenium.By
import uk.gov.hmrc.ui.pages.BasePage

object VendorAgentCheckYourAnswers extends BasePage {

  override def pageUrl: String = "about-the-vendors-agent/check-answers"

  override def pageTitle: String = "Check your answers - About the vendor’s agent - Stamp Taxes Online - GOV.UK"

  val vendorAgentNameChange =
    "a[href ='/stamp-duty-land-tax-filing/about-the-vendors-agent/agent-name/change']"

  val vendorAgentAddressChange =
    "a[href ='/stamp-duty-land-tax-filing/about-the-vendors-agent/agent-address-lookup?changeRoute=change']"

  val doYouWantToAddAgentContactDetails =
    "a[href ='/stamp-duty-land-tax-filing/about-the-vendors-agent/add-agent-contact-details/change']"

  val vendorAgentContactDetails =
    "a[href ='/stamp-duty-land-tax-filing/about-the-vendors-agent/enter-agent-contact-details/change']"

  val doYouWantToAddReference =
    "a[href ='/stamp-duty-land-tax-filing/about-the-vendors-agent/add-agent-reference-number/change']"

  val vendorAgentReferenceNumber =
    "a[href ='/stamp-duty-land-tax-filing/about-the-vendors-agent/enter-agent-reference-number/change']"

  def clickVendorAgentNameChange(): Unit = click(By.cssSelector(vendorAgentNameChange))

  def clickVendorAgentAddressChange(): Unit = click(By.cssSelector(vendorAgentAddressChange))

  def clickAddAgentContactDetailsChange(): Unit = click(By.cssSelector(doYouWantToAddAgentContactDetails))

  def clickVendorAgentContactDetailsChange(): Unit = click(By.cssSelector(vendorAgentContactDetails))

  def clickDoYouWantToAddReferenceChange(): Unit = click(By.cssSelector(doYouWantToAddReference))

  def clickVendorAgentReferenceNumberChange(): Unit = click(By.cssSelector(vendorAgentReferenceNumber))

}
