/*
 * Copyright 2026 HM Revenue & Customs
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

import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, GivenWhenThen}
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.verbs.ShouldVerb
import uk.gov.hmrc.ui.tags.*
import uk.gov.hmrc.selenium.webdriver.{Browser, ScreenshotOnFailure}
import uk.gov.hmrc.ui.pages.*
import uk.gov.hmrc.ui.pages.TaxCalculations.*
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation
import org.openqa.selenium.By

class TaxCalculationsQuestionsSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("SDLT Filing Frontend Tax Calculations") {

// scenario 1 Freehold Tax calculator
    Scenario(
      "Complete the Freehold calculated Tax Calculation Journey",
      TaxCalculationJourney
    ) {

      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(
        HASDIRECT,
        Organisation,
        returnId = Some("freehold-tax-calculated")
      )

      When("the user starts the tax calculations journey")

      // Then("the confirm effective date of the transaction page displayed")
      // TaxCalculationsTotalAmountDue.navigateToPage(
      //   "http://localhost:10910/stamp-duty-land-tax-filing/tax-calculation/confirm-effective-date-of-transaction"
      // )
      // ConfirmEffectiveDate.verifyPageTitle(ConfirmEffectiveDate.pageTitle)
      // ConfirmEffectiveDate.saveAndContinue()
      // Is this effective date of transaction
      // Radio button yes open the before start page
      // Radio button no open effective date question page
      And("the user navigated to tax calculation")
      ReturnTaskList.clickLinkById("task-list-link-tax-calculation-questions")
      Then("the Freehold calculated Before you start page is displayed")
      TaxCalculationsBeforeYouStart.verifyPageTitle(TaxCalculationsBeforeYouStart.pageTitle)
      // User is navigated to calculate SDLT due page
      When("the user want the breakdown page journey")
      Then("the user is navigated to the SDLT breakdown page")
      TaxCalculationsBreakdown.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/tax-calculation/freehold-calculated/SDLT-breakdown"
      )
      TaxCalculationsBreakdown.verifyPageTitle(TaxCalculationsBreakdown.pageTitle)
      When("the user want to go return to the tax calculation page")
      TaxCalculationsBreakdown.clickReturnTaxPage()

      Then("the user is navigated to the calculate SDLT due page")
      CalculateSDLTDue.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/tax-calculation/freehold-calculated/calculated-SDLT-due"
      )
      CalculateSDLTDue.verifyPageTitle(CalculateSDLTDue.pageTitle)
      When("the user want to go return to the tax calculation page")
      // CalculateSDLTDue.saveAndContinue()
      Then("user is navigated to what is the SDLT self-assessment page")
      TaxCalculationsSDLTSelfAssessment.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/tax-calculation/freehold-calculated/SDLT-self-assessment"
      )
      TaxCalculationsSDLTSelfAssessment.verifyPageTitle(
        TaxCalculationsSDLTSelfAssessment.pageTitleFreeholdTax
      )
      When("user enter the amount value and click save and continue button")
      TaxCalculationsSDLTSelfAssessment.input(
        By.id(TaxCalculationsSDLTSelfAssessment.saaValue),
        TaxCalculationsSDLTSelfAssessment.saaInput
      )
      TaxCalculationsSDLTSelfAssessment.saveAndContinue()

      Then("user is navigated to what is the total amount due page")
      TaxCalculationsTotalAmountDue.verifyPageTitle(
        TaxCalculationsTotalAmountDue.pageTitleFreehold
      )
      TaxCalculationsTotalAmountDue.input(
        By.id(TaxCalculationsTotalAmountDue.tppTax),
        TaxCalculationsTotalAmountDue.tppTaxInput
      )
      // TaxCalculationsTotalAmountDue.saveAndContinue()
      Then("the user is navigated to the pay penalties page")
      TaxCalculationsPenalties.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/tax-calculation/freehold-calculated/are-penalties-and-interest-included"
      )
      TaxCalculationsPenalties.verifyPageTitle(TaxCalculationsPenalties.pageTitle)
      When("user selects no radio button and continues")
      TaxCalculationsPenalties.radioButton(TaxCalculationsPenalties.no)
      // TaxCalculationsPenalties.saveAndContinue()
      // User navigated to Check your answers page
      // User clicks on Confirm and Continue
      // User is navigated to Return to TaskList

    }
    // scenario 2 Freehold Self assesed
    Scenario(
      "Complete the Freehold not calculated Tax Calculation Journey",
      TaxCalculationJourney
    ) {

      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(
        HASDIRECT,
        Organisation,
        returnId = Some("freehold-self-assessed")
      )

      When("the user starts the tax calculations journey")
      // Then("the confirm effective date of the transaction page displayed")
      // TaxCalculationsTotalAmountDue.navigateToPage(
      //   "http://localhost:10910/stamp-duty-land-tax-filing/tax-calculation/confirm-effective-date-of-transaction"
      // )
      // ConfirmEffectiveDate.verifyPageTitle(ConfirmEffectiveDate.pageTitle)
      // ConfirmEffectiveDate.saveAndContinue()
      // Is this effective date of transaction
      // Radio button yes open the before start page
      // Radio button no open effective date question page
      And("the user navigated to tax calculation")
      ReturnTaskList.clickLinkById("task-list-link-tax-calculation-questions")
      Then("the Freehold not calculated Before you start page is displayed")
      TaxCalculationsBeforeYouStart.verifyPageTitle(TaxCalculationsBeforeYouStart.pageTitleFreeholdNotCalculated)
      Then("the user is navigated to the calculate SDLT due page")
      CalculateSDLTDue.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/tax-calculation/freehold-not-calculated/HMRC-cannot-calculate-SDLT-due"
      )
      CalculateSDLTDue.verifyPageTitle(CalculateSDLTDue.freeholdSelfAssesedSDLTDuepageTitle)
      // CalculateSDLTDue.saveAndContinue()
      Then("user is navigated to what is the SDLT self-assessment page")
      TaxCalculationsSDLTSelfAssessment.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/tax-calculation/freehold-not-calculated/SDLT-self-assessment"
      )
      TaxCalculationsSDLTSelfAssessment.verifyPageTitle(
        TaxCalculationsSDLTSelfAssessment.pageTitleFreeholdSelfAssesed
      )
      When("user enter the amount value and click save and continue button")
      TaxCalculationsSDLTSelfAssessment.input(
        By.id(TaxCalculationsSDLTSelfAssessment.saaValue),
        TaxCalculationsSDLTSelfAssessment.saaInput
      )
      TaxCalculationsSDLTSelfAssessment.saveAndContinue()
      TaxCalculationsTotalAmountDue.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/tax-calculation/freehold-not-calculated/total-amount-due"
      )
      TaxCalculationsTotalAmountDue.verifyPageTitle(
        TaxCalculationsTotalAmountDue.pageTitleFreeholdSelfAssesedTAD
      )
      When("user enter the amount value and click save and continue button")
      TaxCalculationsTotalAmountDue.input(
        By.id(TaxCalculationsTotalAmountDue.tppTax),
        TaxCalculationsTotalAmountDue.tppTaxInput
      )
      /*TaxCalculationsTotalAmountDue.saveAndContinue()*/

      TaxCalculationsPenalties.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/tax-calculation/freehold-not-calculated/are-penalties-and-interest-included"
      )
      TaxCalculationsPenalties.verifyPageTitle(TaxCalculationsPenalties.pageTitleFreeholdSelfAssesed)

      When("user selects yes radio button and continues")
      TaxCalculationsPenalties.radioButton(TaxCalculationsPenalties.yes)
      // TaxCalculationsPenalties.saveAndContinue()
      // User navigated to Check your answers page
      // User clicks on Confirm and Continue
      // User is navigated to Return to TaskList

    }
    // scenario 3 Leasehold Calculated
    Scenario(
      "Complete the Leasehold calculated Tax Calculation Journey",
      TaxCalculationJourney
    ) {

      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(
        HASDIRECT,
        Organisation,
        returnId = Some("leasehold-tax-calculated")
      )
      When("the user starts the tax calculations journey")
      // Then("the confirm effective date of the transaction page displayed")
      // TaxCalculationsTotalAmountDue.navigateToPage(
      //   "http://localhost:10910/stamp-duty-land-tax-filing/tax-calculation/confirm-effective-date-of-transaction"
      // )
      // ConfirmEffectiveDate.verifyPageTitle(ConfirmEffectiveDate.pageTitle)
      // ConfirmEffectiveDate.saveAndContinue()
      // Is this effective date of transaction
      // Radio button yes open the before start page
      And("the user navigated to tax calculation")
      ReturnTaskList.clickLinkById("task-list-link-tax-calculation-questions")
      Then("the Freehold calculated Before you start page is displayed")
      TaxCalculationsBeforeYouStart.verifyPageTitle(TaxCalculationsBeforeYouStart.pageTitleLeaseholdCalculated)
      // User is navigated to calculate SDLT due page
      // User Click on Hyperlink - check your SDLT breakdown
      // User is navigated to SDLT breakdown page
      // User Click on Hyperlink - Return to tax calculation page
      // User click on Continue button
      Then("user is navigated to what is the SDLT self-assessment page")
      TaxCalculationsSDLTSelfAssessment.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/tax-calculation/leasehold-calculated/SDLT-self-assessment"
      )
      TaxCalculationsSDLTSelfAssessment.verifyPageTitle(
        TaxCalculationsSDLTSelfAssessment.pageTitleLeaseholdTax
      )
      When("user enter the amount value and click save and continue button")
      TaxCalculationsSDLTSelfAssessment.input(
        By.id(TaxCalculationsSDLTSelfAssessment.saaValue),
        TaxCalculationsSDLTSelfAssessment.saaInput
      )
      TaxCalculationsSDLTSelfAssessment.saveAndContinue()

      Then("user is navigated to what is the total amount due page")
      TaxCalculationsTotalAmountDue.verifyPageTitle(
        TaxCalculationsTotalAmountDue.pageTitleLeasehold
      )
      When("user enter the amount value and click save and continue button")
      TaxCalculationsTotalAmountDue.input(
        By.id(TaxCalculationsTotalAmountDue.tppTax),
        TaxCalculationsTotalAmountDue.tppTaxInput
      )
      // TaxCalculationsTotalPremiumValueLeasehold.saveAndContinue()
      Then("the user is navigated to the pay penalties page")
      TaxCalculationsPenalties.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/tax-calculation/leasehold-calculated/are-penalties-and-interest-included"
      )
      TaxCalculationsPenalties.verifyPageTitle(
        TaxCalculationsPenalties.pageTitleLeaseholdCalculated
      )
      When("user selects no radio button and continues")
      TaxCalculationsPenalties.radioButton(TaxCalculationsPenalties.no)
      // TaxCalculationsPenaltiesLeaseholdCalculated.saveAndContinue()
      // Navigate to check your answers page
      // User clicks on Confirm and Continue
      // User is navigated to Return to TaskList
    }

    // scenario 4 Leasehold Calculated
    Scenario(
      "Complete the Leasehold not calculated Tax Calculation Journey",
      TaxCalculationJourney
    ) {

      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(
        HASDIRECT,
        Organisation,
        returnId = Some("leasehold-self-assessed")
      )

      When("the user starts the tax calculations journey")
      // Then("the confirm effective date of the transaction page displayed")
      // TaxCalculationsTotalAmountDue.navigateToPage(
      //   "http://localhost:10910/stamp-duty-land-tax-filing/tax-calculation/confirm-effective-date-of-transaction"
      // )
      // ConfirmEffectiveDate.verifyPageTitle(ConfirmEffectiveDate.pageTitle)
      // ConfirmEffectiveDate.saveAndContinue()
      // Is this effective date of transaction
      // Radio button yes open the before start page
      // Radio button no open effective date question page
      And("the user navigated to tax calculation")
      ReturnTaskList.clickLinkById("task-list-link-tax-calculation-questions")
      Then("the Freehold calculated Before you start page is displayed")
      TaxCalculationsBeforeYouStart.verifyPageTitle(TaxCalculationsBeforeYouStart.pageTitleLeaseholdNotCalculated)
      // User is navigated to HMRC cannot calculate SDLT due page
      // User Click on Continue
      Then("the user is navigated to the total premium value page")
      TaxCalculationsTotalPremiumValueLeaseholdSelfassesed.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/tax-calculation/leasehold-not-calculated/tax-due-on-total-premium-payable"
      )
      TaxCalculationsTotalPremiumValueLeaseholdSelfassesed.verifyPageTitle(
        TaxCalculationsTotalPremiumValueLeaseholdSelfassesed.pageTitle
      )
      When("user enter amount in the box and click continues")
      TaxCalculationsTotalPremiumValueLeaseholdSelfassesed.input(
        By.id(TaxCalculationsTotalPremiumValueLeaseholdSelfassesed.tppTax),
        TaxCalculationsTotalPremiumValueLeaseholdSelfassesed.tppTaxInput
      )
      TaxCalculationsTotalPremiumValueLeaseholdSelfassesed.saveAndContinue()
      Then("user is navigated to what is the tax due on the NPV page")
      TaxDueOnNPV.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/tax-calculation/leasehold-not-calculated/tax-due-on-NPV"
      )
      TaxDueOnNPV.verifyPageTitle(TaxDueOnNPV.pageTitle)
      When("user enter the NPV value and click save and continue button")
      TaxDueOnNPV.input(
        By.id(TaxDueOnNPV.taxDueOnNPVAmountInput),
        TaxDueOnNPV.taxDueOnNPVAmount
      )

      TaxDueOnNPV.saveAndContinue()

      Then("user is navigated to what is the total amount due page")
      TaxCalculationsTotalAmountDue.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/tax-calculation/leasehold-not-calculated/total-amount-due"
      )
      TaxCalculationsTotalAmountDue.verifyPageTitle(
        TaxCalculationsTotalAmountDue.pageTitle
      )
      When("user enter the amount value and click save and continue button")
      TaxCalculationsTotalAmountDue.input(
        By.id(TaxCalculationsTotalAmountDue.tppTax),
        TaxCalculationsTotalAmountDue.tppTaxInput
      )
      // TaxCalculationsTotalAmountDueLeaseholdSelfassesed.saveAndContinue()
      Then("the user is navigated to the pay penalties page")
      TaxCalculationsPenalties.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/tax-calculation/leasehold-not-calculated/are-penalties-and-interest-included"
      )
      TaxCalculationsPenalties.verifyPageTitle(
        TaxCalculationsPenalties.pageTitleLeaseholdNotCalculated
      )
      When("user selects no radio button and continues")
      TaxCalculationsPenalties.radioButton(TaxCalculationsPenalties.no)
      // User is navigated to Check your answers page
      // User clicks on Confirm and Continue
      // User is navigated to Return to TaskList

    }

    // Scenario(
    //   "Comeplte effective date Journey",
    //   TaxCalculationJourney
    // ) {

    //   Given("the user logs in through the Authority Wizard page")
    //   AuthWizard.login(
    //     HASDIRECT,
    //     Organisation,
    //     returnId = Some("leasehold-self-assessed")
    //   )

    //   When("the user starts the tax calculations journey")
    //   // Then("the confirm effective date of the transaction page displayed")
    //   // TaxCalculationsTotalAmountDue.navigateToPage(
    //   //   "http://localhost:10910/stamp-duty-land-tax-filing/tax-calculation/confirm-effective-date-of-transaction"
    //   // )
    //   // ConfirmEffectiveDate.verifyPageTitle(ConfirmEffectiveDate.pageTitle)
    //   // ConfirmEffectiveDate.saveAndContinue()
    //   // Is this effective date of transaction
    //   // Radio button no open effective date question page
    //   // Then("the Effective Date of Transaction page is displayed")
    //   // EffectiveDateOfTransaction.verifyPageTitle(EffectiveDateOfTransaction.pageTitle)

    //   // When("the user enters the effective date of transaction and continues")
    //   // EffectiveDateOfTransaction.enterEffectiveDateOfTransaction()
    //   // EffectiveDateOfTransaction.saveAndContinue()

    // }

  }
}
