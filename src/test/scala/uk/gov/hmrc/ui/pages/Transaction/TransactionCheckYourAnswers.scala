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

package uk.gov.hmrc.ui.pages.Transaction

import org.openqa.selenium.By
import uk.gov.hmrc.ui.pages.BasePage

object TransactionCheckYourAnswers extends BasePage {

  override def pageUrl: String = "about-the-transaction/check-answers"

  override def pageTitle: String = "Check your answers - About the transaction - Stamp Taxes Online - GOV.UK"

  val typeOfTransaction = "a[href = '/stamp-duty-land-tax-filing/about-the-transaction/type-of-transaction/change']"

  val effectiveDateOfTransaction =
    "a[href = '/stamp-duty-land-tax-filing/about-the-transaction/effective-date-of-transaction/change']"

  val addDateOfContract = "a[href = '/stamp-duty-land-tax-filing/about-the-transaction/add-date-of-contract/change']"

  val enterDateOfContract =
    "a[href = '/stamp-duty-land-tax-filing/about-the-transaction/enter-date-of-contract/change']"

  val totalConsiderationOfTransaction =
    "a[href = '/stamp-duty-land-tax-filing/about-the-transaction/total-consideration-of-transaction/change']"

  val isVATIncluded = "a[href = '/stamp-duty-land-tax-filing/about-the-transaction/is-VAT-included/change']"

  val vATAmount = "a[href = '/stamp-duty-land-tax-filing/about-the-transaction/VAT-amount/change']"

  val formsOfConsideration =
    "a[href = '/stamp-duty-land-tax-filing/about-the-transaction/forms-of-consideration/change']"

  val linkedTransactions = "a[href = '/stamp-duty-land-tax-filing/about-the-transaction/linked-transactions/change']"

  val totalConsiderationOfLinkedTransaction =
    "a[href = '/stamp-duty-land-tax-filing/about-the-transaction/total-consideration-of-linked-transaction/change']"

  val claimingRelief = "a[href = '/stamp-duty-land-tax-filing/about-the-transaction/claiming-relief/change']"

  val reasonForRelief = "a[href = '/stamp-duty-land-tax-filing/about-the-transaction/reason-for-relief/change']"

  val addRegisteredCharityNumber =
    "a[href = '/stamp-duty-land-tax-filing/about-the-transaction/add-registered-charity-number/change']"

  val enterRegisteredCharityNumber =
    "a[href = '/stamp-duty-land-tax-filing/about-the-transaction/enter-registered-charity-number/change']"

  val partialRelief = "a[href = '/stamp-duty-land-tax-filing/about-the-transaction/partial-relief/change']"

  val claimingPartialRelief =
    "a[href = '/stamp-duty-land-tax-filing/about-the-transaction/claiming-partial-relief/change']"

  val deferringPayment = "a[href= '/stamp-duty-land-tax-filing/about-the-transaction/deferring-payment/change']"

  val uncertainFutureEvent =
    "a[href= '/stamp-duty-land-tax-filing/about-the-transaction/considerations-affected-by-uncertain-future-events/change']"

  val useOfLandOrProperty =
    "a[href= '/stamp-duty-land-tax-filing/about-the-transaction/use-of-land-or-property/change']"

  val saleOfBusiness = "a[href= '/stamp-duty-land-tax-filing/about-the-transaction/sale-of-a-business/change']"

  val CAP1OrNSBC = "a[href= '/stamp-duty-land-tax-filing/about-the-transaction/CAP1-or-NSBC/change']"

  val CAP1OrNSBCFollowed =
    "a[href= '/stamp-duty-land-tax-filing/about-the-transaction/description-of-CAP1-or-NSBC/change']"

  val RestrictionsConvenantsAndConditions =
    "a[href= '/stamp-duty-land-tax-filing/about-the-transaction/restrictions-covenants-and-conditions/change']"

  val ExchangeOrPartExchange =
    "a[href= '/stamp-duty-land-tax-filing/about-the-transaction/exchange-or-part-exchange/change']"

  val ExercisingAnOption = "a[href= '/stamp-duty-land-tax-filing/about-the-transaction/exercising-an-option/change']"

  val DescriptionOfRestrictionsConvenantsAndConditions =
    "a[href= '/stamp-duty-land-tax-filing/about-the-transaction/description-of-restrictions-covenants-and-conditions/change']"

  val WhatIsIncludedInTheSaleOfBusiness =
    "a[href= '/stamp-duty-land-tax-filing/about-the-transaction/assets-included-in-sale-of-the-business/change']"

  val TotalConsiderationOfAssets =
    "a[href= '/stamp-duty-land-tax-filing/about-the-transaction/total-consideration-of-all-assets/change']"

  val AddressOfPropertyExchangedOrPartExchanged =
    "a[href= '/stamp-duty-land-tax-filing/about-the-transaction/transaction-address-lookup?changeRoute=change']"

  val IsPurchaserRegisteredWithCIS =
    "a[href= '/stamp-duty-land-tax-filing/about-the-transaction/is-the-purchaser-registered-with-CIS/change']"

  val CISRegistrationNumber =
    "a[href= '/stamp-duty-land-tax-filing/about-the-transaction/what-is-the-CIS-number/change']"

  def clickTypeOfTransaction(): Unit = click(By.cssSelector(typeOfTransaction))

  def clickEffectiveDateOfTransaction(): Unit = click(By.cssSelector(effectiveDateOfTransaction))

  def clickAddDateOfContract(): Unit = click(By.cssSelector(addDateOfContract))

  def clickEnterDateOfContract(): Unit = click(By.cssSelector(enterDateOfContract))

  def clickTotalConsiderationOfTransaction(): Unit = click(By.cssSelector(totalConsiderationOfTransaction))

  def clickIsVATIncluded(): Unit = click(By.cssSelector(isVATIncluded))

  def clickVATAmount(): Unit = click(By.cssSelector(vATAmount))

  def clickFormsOfConsideration(): Unit = click(By.cssSelector(formsOfConsideration))

  def clickLinkedTransactions(): Unit = click(By.cssSelector(linkedTransactions))

  def clickTotalConsiderationOfLinkedTransaction(): Unit = click(By.cssSelector(totalConsiderationOfLinkedTransaction))

  def clickClaimingRelief(): Unit = click(By.cssSelector(claimingRelief))

  def clickReasonForRelief(): Unit = click(By.cssSelector(reasonForRelief))

  def clickAddRegisteredCharityNumber(): Unit = click(By.cssSelector(addRegisteredCharityNumber))

  def clickEnterRegisteredCharityNumber(): Unit = click(By.cssSelector(enterRegisteredCharityNumber))

  def clickPartialRelief(): Unit = click(By.cssSelector(partialRelief))

  def clickClaimingPartialRelief(): Unit = click(By.cssSelector(claimingPartialRelief))

  def clickDeferringPayment(): Unit = click(By.cssSelector(deferringPayment))

  def clickUncertainFutureEvent(): Unit = click(By.cssSelector(uncertainFutureEvent))

  def clickUseOfLandOrProperty(): Unit = click(By.cssSelector(useOfLandOrProperty))

  def clickSaleOfBusiness(): Unit = click(By.cssSelector(saleOfBusiness))

  def clickCAP1OrNSBC(): Unit = click(By.cssSelector(CAP1OrNSBC))

  def clickRestrictionsConvenantsAndConditions(): Unit = click(By.cssSelector(RestrictionsConvenantsAndConditions))

  def clickExchangeOrPartExchange(): Unit = click(By.cssSelector(ExchangeOrPartExchange))

  def clickExercisingAnOption(): Unit = click(By.cssSelector(ExercisingAnOption))

  def clickDescriptionOfRestrictionsConvenantsAndConditions(): Unit = click(
    By.cssSelector(DescriptionOfRestrictionsConvenantsAndConditions)
  )

  def clickWhatIsIncludedInTheSaleOfBusiness(): Unit = click(By.cssSelector(WhatIsIncludedInTheSaleOfBusiness))

  def clickTotalConsiderationOfAssets(): Unit = click(By.cssSelector(TotalConsiderationOfAssets))

  def clickCAP1OrNSBCFollowed(): Unit = click(By.cssSelector(CAP1OrNSBCFollowed))

  def clickAddressOfPropertyExchangedOrPartExchanged(): Unit = click(
    By.cssSelector(AddressOfPropertyExchangedOrPartExchanged)
  )

  def clickIsPurchaserRegisteredWithCIS(): Unit = click(By.cssSelector(IsPurchaserRegisteredWithCIS))

  def clickCISRegistrationNumber(): Unit = click(By.cssSelector(CISRegistrationNumber))

}
