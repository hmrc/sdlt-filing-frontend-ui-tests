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
      // Below step to be uncommented once the next page is ready
      // TypeOfLease.saveAndContinue()

      /*
        Lease start date page is shown
        User selects the Lease start date and continues
        Lease end date page is shown
        User selects the Lease end date and continues
        Rent free periods page is shown
        User selects the yes for rent-free periods and continues
       */

      // Below step to removed once the navigation is ready
      RentFreePeriod.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/about-the-lease/enter-rent-free-period"
      )
      Then("the user is navigated to Calculating the rent-free periods Page")
      RentFreePeriod.verifyPageTitle(RentFreePeriod.pageTitle)

      When("the user enters the rent-free periods months and continues")
      RentFreePeriod.input(By.id(RentFreePeriod.rentFreePeriod), RentFreePeriod.inputRentFreePeriod)
      // Below step to be uncommented once the next page is ready
      // RentFreePeriod.saveAndContinue()

      /*
        Annual starting rent page is shown
        User enters the annual starting rent and continues
        End of annual starting rent page is shown
        User selects the end date of annual starting rent and continues
        Later rent page is shown
       */

      // Below step to removed once the navigation is ready
      LaterRent.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/about-the-lease/later-rent"
      )
      Then("the user is navigated to Later rent Page")
      LaterRent.verifyPageTitle(LaterRent.pageTitle)

      When("the user selects Yes and continues")
      LaterRent.radioButton(LaterRent.yes)
      LaterRent.saveAndContinue()
      Then("the ThousandPoundThreshold page is displayed")
      ThousandPoundThreshold.verifyPageTitle(ThousandPoundThreshold.pageTitle)

      When("the user confirms the annual rent is £1000 or more")
      ThousandPoundThreshold.radioButton(ThousandPoundThreshold.yes)
      // Below step to be uncommented once the next page is ready
      //      ThousandPoundThreshold.saveAndContinue()

      /*
       Annual rent VAT page is shown
       User selects the yes for annual rent VAT and continues
       Total amount of VAT payable on annual rent is shown
        User enters the total amount of VAT payable on annual rent and continues
       Total premium payable page is shown
       User selects the yes for total premium payable and continues
       Calculating the total premium payable including VAT page is shown
       User enters the total premium payable including VAT and continues
       Net present value page is shown
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
      // Below step to be uncommented once the next page is ready
      // TypeOfLease.saveAndContinue()

      /*
        Lease start date page is shown
        User selects the Lease start date and continues
        Lease end date page is shown
        User selects the Lease end date and continues
        Rent free periods page is shown
        User selects the yes for rent - free periods and continues
       */

      // Below step to removed once the navigation is ready
      RentFreePeriod.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/about-the-lease/enter-rent-free-period"
      )
      Then("the user is navigated to Calculating the rent-free periods Page")
      RentFreePeriod.verifyPageTitle(RentFreePeriod.pageTitle)

      When("the user enters the rent-free periods months and continues")
      RentFreePeriod.input(By.id(RentFreePeriod.rentFreePeriod), RentFreePeriod.inputRentFreePeriod)
      // Below step to be uncommented once the next page is ready
      // RentFreePeriod.saveAndContinue()

      /*
        Annual starting rent page is shown
        User enters the annual starting rent and continues
        End of annual starting rent page is shown
        User selects the end date of annual starting rent and continues
       */

      LaterRent.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/about-the-lease/later-rent"
      )
      Then("the user is navigated to Later rent Page")
      LaterRent.verifyPageTitle(LaterRent.pageTitle)

      When("the user selects Yes and continues")
      LaterRent.radioButton(LaterRent.no)
      LaterRent.saveAndContinue()
      Then("the ThousandPoundThreshold page is displayed")
      ThousandPoundThreshold.verifyPageTitle(ThousandPoundThreshold.pageTitle)

      When("the user confirms the annual rent is £1000 or more")
      ThousandPoundThreshold.radioButton(ThousandPoundThreshold.yes)
      // Below step to uncommented once the next page is ready
      //      ThousandPoundThreshold.saveAndContinue()

      /*
        Annual rent VAT page is shown
        User selects the yes for annual rent VAT and continues
        Total amount of VAT payable on annual rent is shown
        User enters the total amount of VAT payable on annual rent and continues
        Check your answers page is shown
        User checks the answers and continues
        Overview page is shown
       */
    }
  }
}
