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

package uk.gov.hmrc.ui.specs

import org.openqa.selenium.By
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.verbs.ShouldVerb
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, GivenWhenThen}
import uk.gov.hmrc.selenium.webdriver.{Browser, ScreenshotOnFailure}
import uk.gov.hmrc.ui.pages.*
import uk.gov.hmrc.ui.pages.Lease.*
import uk.gov.hmrc.ui.tags.*
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation

class LeaseQuestionsSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("SDLT Filing Frontend Lease Questions") {

    Scenario(
      "Complete the Lease Questions journey when transaction type is selected as L-grant of lease",
      LeaseJourney
    ) {

      Given("the user is logged in through the AuthWizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("prelimTransactionL-property-type-residential"))

      When("the user starts the Lease questions")
      ReturnTaskList.clickLinkById("task-list-link-lease-questions")
      Then("Lease questions Before you start page is shown")
      LeaseBeforeYouStart.verifyPageTitle(LeaseBeforeYouStart.pageTitle)

      When("the user click continue on Before You Start Page")
      LeaseBeforeYouStart.saveAndContinue()
      Then("the user is navigated to Type of Lease Page")
      TypeOfLease.verifyPageTitle(TypeOfLease.pageTitle)

      When("the user selects Residential and clicks continue")
      TypeOfLease.radioButton(TypeOfLease.residential)
      TypeOfLease.saveAndContinue()
      Then("the user is navigated to Lease start date page")
      LeaseStartDate.verifyPageTitle(LeaseStartDate.pageTitle)

      When("the user enters the lease start date and continues")
      LeaseStartDate.enterLeaseStartDate()
      LeaseStartDate.saveAndContinue()
      Then("Lease end date page is shown")
      LeaseEndDate.verifyPageTitle(LeaseEndDate.pageTitle)

      When("the user enters the lease end date and continues")
      LeaseEndDate.enterLeaseEndDate()
      LeaseEndDate.saveAndContinue()
      Then("the user is navigated to Rent Free Period Page")
      AddRentFreePeriod.verifyPageTitle(AddRentFreePeriod.pageTitle)

      When("the user selects Yes and continues")
      AddRentFreePeriod.radioButton(AddRentFreePeriod.yes)
      AddRentFreePeriod.saveAndContinue()
      Then("the user is navigated to Calculating the rent-free periods Page")
      RentFreePeriod.verifyPageTitle(RentFreePeriod.pageTitle)

      When("the user enters the rent-free periods months and continues")
      RentFreePeriod.input(By.id(RentFreePeriod.rentFreePeriod), RentFreePeriod.inputRentFreePeriod)
      RentFreePeriod.saveAndContinue()
      Then("the user is navigated to Annual starting rent page")
      AnnualStartingRent.verifyPageTitle(AnnualStartingRent.pageTitle)
      And("the validates the drop down text About variable or uncertain rent")
      AnnualStartingRent.clickDropdownText()
      AnnualStartingRent.verifyPageText(AnnualStartingRent.dropdownText, 2)

      When("the user enters annual rent and continues")
      AnnualStartingRent.input(By.id(AnnualStartingRent.annualStartingRent), AnnualStartingRent.annualStartingRentInput)
      AnnualStartingRent.saveAndContinue()
      Then("the EndOfAnnualStartingRent page is shown")
      EndOfAnnualStartingRent.verifyPageTitle(EndOfAnnualStartingRent.pageTitle)

      When("the user provides the end date for starting rent")
      EndOfAnnualStartingRent.enterEndOfAnnualStartingRent()
      EndOfAnnualStartingRent.saveAndContinue()
      Then("the user is navigated to Later rent Page")
      LaterRent.verifyPageTitle(LaterRent.pageTitle)

      When("the user selects Yes and continues")
      LaterRent.radioButton(LaterRent.yes)
      LaterRent.saveAndContinue()
      Then("the ThousandPoundThreshold page is displayed")
      ThousandPoundThreshold.verifyPageTitle(ThousandPoundThreshold.pageTitle)

      When("the user confirms the annual rent is £1000 or more")
      ThousandPoundThreshold.radioButton(ThousandPoundThreshold.yes)
      ThousandPoundThreshold.saveAndContinue()
      Then("Annual rent VAT page is shown")
      AnnualRentVAT.verifyPageTitle(AnnualRentVAT.pageTitle)

      When("the user selects Yes and continues")
      AnnualRentVAT.radioButton(AnnualRentVAT.yes)
      AnnualRentVAT.saveAndContinue()
      Then("the user navigates to Total amount of VAT payable on annual rent")
      EnterAnnualRentVATAmount.verifyPageTitle(EnterAnnualRentVATAmount.pageTitle)

      When("the user enters the total amount of VAT payable on annual rent and continues")
      EnterAnnualRentVATAmount.input(
        By.id(EnterAnnualRentVATAmount.annualRentVATAmount),
        EnterAnnualRentVATAmount.annualRentVATAmountInput
      )
      EnterAnnualRentVATAmount.saveAndContinue()

      /*  Total premium payable page is shown
      User selects the yes for total premium payable and continues
       */
      Then("Calculating the total premium payable including VAT page is shown")
      // Below step to be removed once the navigation is ready
      TotalPremiumPayable.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/about-the-lease/enter-total-premium-payable"
      )
      TotalPremiumPayable.verifyPageTitle(TotalPremiumPayable.pageTitle)
      Then("User enters the total premium payable including VAT and continues")
      TotalPremiumPayable.input(
        By.id(TotalPremiumPayable.TotalPremiumPayable),
        TotalPremiumPayable.TotalPremiumPayableInput
      )
      // Below step to be uncommented once the navigation is ready
      TotalPremiumPayable.saveAndContinue()

      /* Net present value page is shown
      User enters the net present value and continues
      Check your answers page is shown
      User checks the answers and continues
      Overview page is shown
       */
    }

    Scenario(
      "Complete the Lease Questions journey when transaction type is not L-grant of lease",
      LeaseJourney
    ) {
      Given("the user is logged in through the AuthWizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("prelimTransactionL-property-type-mixed"))

      When("the user starts the Lease questions")
      ReturnTaskList.clickLinkById("task-list-link-lease-questions")
      Then("Lease questions Before you start page is shown")
      LeaseBeforeYouStart.verifyPageTitle(LeaseBeforeYouStart.pageTitle)

      When("the user click continue on Before You Start Page")
      LeaseBeforeYouStart.saveAndContinue()
      Then("the user is naviagted to Type of Lease Page")
      TypeOfLease.verifyPageTitle(TypeOfLease.pageTitle)

      When("the user selects Residential and clicks continue")
      TypeOfLease.radioButton(TypeOfLease.mixedUse)
      TypeOfLease.saveAndContinue()
      Then("the user is navigated to Lease start date page")
      LeaseStartDate.verifyPageTitle(LeaseStartDate.pageTitle)

      When("the user enters the lease start date and continues")
      LeaseStartDate.enterLeaseStartDate()
      LeaseStartDate.saveAndContinue()
      Then("lease end date page is shown")
      LeaseEndDate.verifyPageTitle(LeaseEndDate.pageTitle)

      When("the user enters the lease end date and continues")
      LeaseEndDate.enterLeaseEndDate()
      LeaseEndDate.saveAndContinue()
      Then("the user is navigated to Rent Free Period Page")
      AddRentFreePeriod.verifyPageTitle(AddRentFreePeriod.pageTitle)

      When("the user selects Yes and continues")
      AddRentFreePeriod.radioButton(AddRentFreePeriod.no)
      AddRentFreePeriod.saveAndContinue()
      Then("the user is navigated to Annual starting rent page")
      AnnualStartingRent.verifyPageTitle(AnnualStartingRent.pageTitle)

      When("the user enters annual rent and continues")
      AnnualStartingRent.input(By.id(AnnualStartingRent.annualStartingRent), AnnualStartingRent.annualStartingRentInput)
      AnnualStartingRent.saveAndContinue()
      Then("the EndOfAnnualStartingRent page is shown")
      EndOfAnnualStartingRent.verifyPageTitle(EndOfAnnualStartingRent.pageTitle)

      When("the user provides the end date for starting rent")
      EndOfAnnualStartingRent.enterEndOfAnnualStartingRent()
      EndOfAnnualStartingRent.saveAndContinue()
      Then("the user is navigated to Later rent Page")
      LaterRent.verifyPageTitle(LaterRent.pageTitle)

      When("the user selects Yes and continues")
      LaterRent.radioButton(LaterRent.no)
      LaterRent.saveAndContinue()
      Then("the ThousandPoundThreshold page is displayed")
      ThousandPoundThreshold.verifyPageTitle(ThousandPoundThreshold.pageTitle)

      When("the user confirms the annual rent is £1000 or more")
      ThousandPoundThreshold.radioButton(ThousandPoundThreshold.yes)
      ThousandPoundThreshold.saveAndContinue()
      Then("Annual rent VAT page is shown")
      AnnualRentVAT.verifyPageTitle(AnnualRentVAT.pageTitle)

      When("the user selects Yes and continues")
      AnnualRentVAT.radioButton(AnnualRentVAT.yes)
      AnnualRentVAT.saveAndContinue()
      Then("the user navigates to Total amount of VAT payable on annual rent")
      EnterAnnualRentVATAmount.verifyPageTitle(EnterAnnualRentVATAmount.pageTitle)

      When("the user enters the total amount of VAT payable on annual rent and continues")
      EnterAnnualRentVATAmount.input(
        By.id(EnterAnnualRentVATAmount.annualRentVATAmount),
        EnterAnnualRentVATAmount.annualRentVATAmountInput
      )
      EnterAnnualRentVATAmount.saveAndContinue()
      /*   Check your answers page is shown
      User checks the answers and continues
      Overview page is shown
       */
    }
  }
}
