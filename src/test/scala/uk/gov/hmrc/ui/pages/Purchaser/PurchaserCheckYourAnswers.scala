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

object PurchaserCheckYourAnswers extends BasePage {

  override def pageUrl: String = "about-the-purchaser/check-answers"

  override def pageTitle: String = "Check your answers - About the purchaser - Stamp Taxes Online - GOV.UK"

  val purchaserTypeChange =
    "a[href ='/stamp-duty-land-tax-filing/about-the-purchaser/who-is-making-the-purchase/change']"

  val purchaserNameChange = "a[href ='/stamp-duty-land-tax-filing/about-the-purchaser/purchaser-name/change']"

  val purchaserAddressChange =
    "a[href ='/stamp-duty-land-tax-filing/about-the-purchaser/purchaser-address-lookup?changeRoute=change']"

  val doYouWantToAddPhoneNumber =
    "a[href ='/stamp-duty-land-tax-filing/about-the-purchaser/add-purchaser-phone-number/change']"

  val purchaserPhoneNumber =
    "a[href ='/stamp-duty-land-tax-filing/about-the-purchaser/enter-purchaser-phone-number/change']"

  val doPurchaserHaveNINumber =
    "a[href ='/stamp-duty-land-tax-filing/about-the-purchaser/add-national-insurance-number/change']"

  val purchaserNINumber =
    "a[href ='/stamp-duty-land-tax-filing/about-the-purchaser/enter-national-insurance-number/change']"

  val purchaserDOB = "a[href ='/stamp-duty-land-tax-filing/about-the-purchaser/date-of-birth/change']"

  val indivFormOfID = "a[href ='/stamp-duty-land-tax-filing/about-the-purchaser/form-of-id-individual/change']"

  val formOfID = "a[href ='/stamp-duty-land-tax-filing/about-the-purchaser/confirm-purchaser-identity/change']"

  val VATRegistrationNumber =
    "a[href ='/stamp-duty-land-tax-filing/about-the-purchaser/vat-registration-number/change']"

  val utrNumber = "a[href = '/stamp-duty-land-tax-filing/about-the-purchaser/corporation-tax-utr/change']"

  val idAndCountryIssued = "a[href ='/stamp-duty-land-tax-filing/about-the-purchaser/form-of-id-company/change']"

  val typeOfCompany = "a[href ='/stamp-duty-land-tax-filing/about-the-purchaser/type-of-company/change']"

  val isCompanyActingAsTrustee = "a[href ='/stamp-duty-land-tax-filing/about-the-purchaser/acting-as-a-trustee/change']"

  val areCompanyAndVendorConnected =
    "a[href ='/stamp-duty-land-tax-filing/about-the-purchaser/purchaser-and-vendor-connected/change']"

  val doYouKnowTypeOfCompany = "a[href ='/stamp-duty-land-tax-filing/about-the-purchaser/add-type-of-company/change']"

  def clickPurchaserNameChange(): Unit = click(By.cssSelector(purchaserNameChange))

  def clickPurchaserTypeChange(): Unit = click(By.cssSelector(purchaserTypeChange))

  def clickPurchaserAddressChange(): Unit = click(By.cssSelector(purchaserAddressChange))

  def clickAddPhoneNumberChange(): Unit = click(By.cssSelector(doYouWantToAddPhoneNumber))

  def clickPurchaserPhoneNumberChange(): Unit = click(By.cssSelector(purchaserPhoneNumber))

  def clickDoPurchaserHaveNINumberChange(): Unit = click(By.cssSelector(doPurchaserHaveNINumber))

  def clickPurchaserNINumberChange(): Unit = click(By.cssSelector(purchaserNINumber))

  def clickPurchaserDOBChange(): Unit = click(By.cssSelector(purchaserDOB))

  def clickIndivFormOfIDChange(): Unit = click(By.cssSelector(indivFormOfID))

  def clickFormOfIDChange(): Unit = click(By.cssSelector(formOfID))

  def clickVATRegistrationNumberChange(): Unit = click(By.cssSelector(VATRegistrationNumber))

  def clickUTRNumberChange(): Unit = click(By.cssSelector(utrNumber))

  def clickIdAndCountryIssuedChange(): Unit = click(By.cssSelector(idAndCountryIssued))

  def clickTypeOfCompanyChange(): Unit = click(By.cssSelector(typeOfCompany))

  def clickIsCompanyActingAsTrusteeChange(): Unit = click(By.cssSelector(isCompanyActingAsTrustee))

  def clickAreCompanyAndVendorConnectedChange(): Unit = click(By.cssSelector(areCompanyAndVendorConnected))

  def clickDoYouKnowCompanyNameChange(): Unit = click(By.cssSelector(doYouKnowTypeOfCompany))
}
