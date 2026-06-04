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

package uk.gov.hmrc.ui.pages.Lease

import org.openqa.selenium.By
import uk.gov.hmrc.ui.pages.BasePage

object LeaseCheckYourAnswers extends BasePage {

  override def pageUrl: String = "about-the-lease/check-answers"

  override def pageTitle: String = "Check your answers - About the lease - Stamp Taxes Online - GOV.UK"

  val typeOfLeaseChange = "a[href = '/stamp-duty-land-tax-filing/about-the-lease/type-of-lease/change']"

  val leaseStartDateChange = "a[href = '/stamp-duty-land-tax-filing/about-the-lease/lease-start-date/change']"

  val leaseEndDate = "a[href = '/stamp-duty-land-tax-filing/about-the-lease/lease-end-date/change']"

  val doesLeaseIncludeFreePeriod = "a[href = '/stamp-duty-land-tax-filing/about-the-lease/add-rent-free-period/change']"

  val rentFreePeriod = "a[href = '/stamp-duty-land-tax-filing/about-the-lease/enter-rent-free-period/change']"

  val annualStartingRent = "a[href = '/stamp-duty-land-tax-filing/about-the-lease/annual-starting-rent/change']"

  val endOfAnnualStartingRent =
    "a[href = '/stamp-duty-land-tax-filing/about-the-lease/end-of-annual-starting-rent/change']"

  val laterRentKnown = "a[href = '/stamp-duty-land-tax-filing/about-the-lease/later-rent/change']"

  val annualRent1000rMore = "a[href = '/stamp-duty-land-tax-filing/about-the-lease/1000-pound-threshold/change']"

  val IsVatPayableOnRent = "a[href = '/stamp-duty-land-tax-filing/about-the-lease/add-annual-rent-VAT/change']"

  val TotalPremiumPayableIncludingVAT =
    "a[href = '/stamp-duty-land-tax-filing/about-the-lease/enter-total-premium-payable/change']"

  val NetPresentValue = "a[href = '/stamp-duty-land-tax-filing/about-the-lease/net-present-value-NPV/change']"

  def clickTypeOfLease(): Unit = click(By.cssSelector(typeOfLeaseChange))

  def clickLeaseStartDate(): Unit = click(By.cssSelector(leaseStartDateChange))

  def clickLeaseEndDate(): Unit = click(By.cssSelector(leaseEndDate))

  def clickDoesLeaseIncludeFreePeriod(): Unit = click(By.cssSelector(doesLeaseIncludeFreePeriod))

  def clickRentFreePeriod(): Unit = click(By.cssSelector(rentFreePeriod))

  def clickAnnualStartingRent(): Unit = click(By.cssSelector(annualStartingRent))

  def clickEndOfAnnualStartingRent(): Unit = click(By.cssSelector(endOfAnnualStartingRent))

  def clickLaterRentKnown(): Unit = click(By.cssSelector(laterRentKnown))

  def clickAnnualRent1000rMore(): Unit = click(By.cssSelector(annualRent1000rMore))

  def clickIsVatPayableOnRent(): Unit = click(By.cssSelector(IsVatPayableOnRent))

  def clickTotalPremiumPayableIncludingVAT(): Unit = click(By.cssSelector(TotalPremiumPayableIncludingVAT))

  def clickNetPresentValue(): Unit = click(By.cssSelector(NetPresentValue))

}
