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
      And("the user navigated to tax calculation")
      ReturnTaskList.clickLinkById("task-list-link-tax-calculation-questions")

      When("the user confirms the effective date of the transaction and continues")
      ConfirmEffectiveDate.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/tax-calculation/confirm-effective-date-of-transaction"
      )
      ConfirmEffectiveDate.verifyPageTitle(ConfirmEffectiveDate.pageTitle)

      ConfirmEffectiveDate.saveAndContinue()

      Then("the user is navigated to Is this effective date of transaction page")

      IsThisEffectiveDateOfTransaction.verifyPageTitle(IsThisEffectiveDateOfTransaction.pageTitle)
      When("the user selects yes radio button and continues")
      IsThisEffectiveDateOfTransaction.radioButton(IsThisEffectiveDateOfTransaction.yes)
      IsThisEffectiveDateOfTransaction.saveAndContinue()

      Then("the Freehold calculated Before you start page is displayed")
      TaxCalculationsBeforeYouStart.verifyPageTitle(TaxCalculationsBeforeYouStart.pageTitle)
      TaxCalculationsBeforeYouStart.saveAndContinue()
      Then("the user is navigated to the calculate SDLT due page")
      CalculateSDLTDue.verifyPageTitle(CalculateSDLTDue.pageTitle)

      When("the user want the breakdown page journey")
      CalculateSDLTDue.clickSDLTBreakDownLink()
      Then("the user is navigated to the SDLT breakdown page")

      TaxCalculationsBreakdown.verifyPageTitle(TaxCalculationsBreakdown.pageTitle)
      When("the user want to go return to the tax calculation page")
      TaxCalculationsBreakdown.clickReturnTaxPage()

      Then("the user is navigated to the calculate SDLT due page")

      CalculateSDLTDue.verifyPageTitle(CalculateSDLTDue.pageTitle)
      When("the user want to go return to the tax calculation page")
      CalculateSDLTDue.saveAndContinue()
      Then("user is navigated to what is the SDLT self-assessment page")

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
      TaxCalculationsTotalAmountDue.saveAndContinue()
      Then("the user is navigated to the pay penalties page")

      TaxCalculationsPenalties.verifyPageTitle(TaxCalculationsPenalties.pageTitle)
      When("user selects no radio button and continues")
      TaxCalculationsPenalties.radioButton(TaxCalculationsPenalties.no)
      TaxCalculationsPenalties.saveAndContinue()

      Then("the user is navigated to the check your answers page")
      TaxCalculationCheckYourAnswers.verifyPageTitle(TaxCalculationCheckYourAnswers.pageTitle)

      When("the user clicks on change link and enter self assesed sdlt amount")
      TaxCalculationCheckYourAnswers.clickselfAssessedSDLTAmountChange()
      TaxCalculationsSDLTSelfAssessment.input(
        By.id(TaxCalculationsSDLTSelfAssessment.saaValue),
        TaxCalculationsSDLTSelfAssessment.saaInput
      )
      TaxCalculationsSDLTSelfAssessment.saveAndContinue()
      Then("the user is navigated to the check your answers page")
      TaxCalculationCheckYourAnswers.verifyPageTitle(TaxCalculationCheckYourAnswers.pageTitle)

      When("the user clicks on change link and enter amount to be paid")
      TaxCalculationCheckYourAnswers.clickamountTobePaidChange()
      TaxCalculationsTotalAmountDue.input(
        By.id(TaxCalculationsTotalAmountDue.tppTax),
        TaxCalculationsTotalAmountDue.tppTaxInput
      )
      TaxCalculationsTotalAmountDue.saveAndContinue()
      Then("the user is navigated to the check your answers page")
      TaxCalculationCheckYourAnswers.verifyPageTitle(TaxCalculationCheckYourAnswers.pageTitle)

      When("the user clicks on change link and change yes to pay penalties page")
      TaxCalculationCheckYourAnswers.clickpenaltiesChange()
      TaxCalculationsPenalties.radioButton(TaxCalculationsPenalties.yes)
      TaxCalculationsPenalties.saveAndContinue()
      Then("the user is navigated to the check your answers page")
      TaxCalculationCheckYourAnswers.verifyPageTitle(TaxCalculationCheckYourAnswers.pageTitle)
      // checkYourAnswers.saveAndContinue()
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

      And("the user navigated to tax calculation")
      ReturnTaskList.clickLinkById("task-list-link-tax-calculation-questions")

      Then("the confirm effective date of the transaction page displayed")
      TaxCalculationsTotalAmountDue.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/tax-calculation/confirm-effective-date-of-transaction"
      )
      ConfirmEffectiveDate.verifyPageTitle(ConfirmEffectiveDate.pageTitle)

      ConfirmEffectiveDate.saveAndContinue()

      Then("the user is navigated to Is this effective date of transaction page")

      IsThisEffectiveDateOfTransaction.verifyPageTitle(IsThisEffectiveDateOfTransaction.pageTitle)
      When("the user selects yes radio button and continues")
      IsThisEffectiveDateOfTransaction.radioButton(IsThisEffectiveDateOfTransaction.yes)
      IsThisEffectiveDateOfTransaction.saveAndContinue()

      Then("the Freehold not calculated Before you start page is displayed")
      TaxCalculationsBeforeYouStart.verifyPageTitle(TaxCalculationsBeforeYouStart.pageTitleFreeholdNotCalculated)
      TaxCalculationsBeforeYouStart.saveAndContinue()
      CalculateSDLTDue.verifyPageTitle(CalculateSDLTDue.freeholdSelfAssesedSDLTDuepageTitle)
      CalculateSDLTDue.saveAndContinue()
      // Total Premium payable page
      Then("user is navigated to what is the SDLT self-assessment page")

      TaxCalculationsSDLTSelfAssessment.verifyPageTitle(
        TaxCalculationsSDLTSelfAssessment.pageTitleFreeholdSelfAssesed
      )
      When("user enter the amount value and click save and continue button")
      TaxCalculationsSDLTSelfAssessment.input(
        By.id(TaxCalculationsSDLTSelfAssessment.saaValue),
        TaxCalculationsSDLTSelfAssessment.saaInput
      )
      TaxCalculationsSDLTSelfAssessment.saveAndContinue()

      TaxCalculationsTotalAmountDue.verifyPageTitle(
        TaxCalculationsTotalAmountDue.pageTitleFreeholdSelfAssesedTAD
      )
      When("user enter the amount value and click save and continue button")
      TaxCalculationsTotalAmountDue.input(
        By.id(TaxCalculationsTotalAmountDue.tppTax),
        TaxCalculationsTotalAmountDue.tppTaxInput
      )
      TaxCalculationsTotalAmountDue.saveAndContinue()

      TaxCalculationsPenalties.verifyPageTitle(TaxCalculationsPenalties.pageTitleFreeholdSelfAssesed)

      When("user selects yes radio button and continues")
      TaxCalculationsPenalties.radioButton(TaxCalculationsPenalties.yes)
      TaxCalculationsPenalties.saveAndContinue()
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

      And("the user navigated to tax calculation")
      ReturnTaskList.clickLinkById("task-list-link-tax-calculation-questions")

      Then("the confirm effective date of the transaction page displayed")
      TaxCalculationsTotalAmountDue.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/tax-calculation/confirm-effective-date-of-transaction"
      )
      ConfirmEffectiveDate.verifyPageTitle(ConfirmEffectiveDate.pageTitle)

      ConfirmEffectiveDate.saveAndContinue()

      Then("the user is navigated to Is this effective date of transaction page")

      IsThisEffectiveDateOfTransaction.verifyPageTitle(IsThisEffectiveDateOfTransaction.pageTitle)
      When("the user selects yes radio button and continues")
      IsThisEffectiveDateOfTransaction.radioButton(IsThisEffectiveDateOfTransaction.yes)
      IsThisEffectiveDateOfTransaction.saveAndContinue()

      Then("the Freehold calculated Before you start page is displayed")
      TaxCalculationsBeforeYouStart.verifyPageTitle(TaxCalculationsBeforeYouStart.pageTitleLeaseholdCalculated)
      TaxCalculationsBeforeYouStart.saveAndContinue()
      Then("the user is navigated to the calculate SDLT due page")
      CalculateSDLTDue.verifyPageTitle(CalculateSDLTDue.leaseholdSDLTDuepageTitle)
      CalculateSDLTDue.clickSDLTBreakDownLink()
      Then("the user is navigated to the SDLT breakdown page")
      TaxCalculationsBreakdown.verifyPageTitle(TaxCalculationsBreakdown.leaseholdSDLTBreakdownpageTitle)
      When("the user click retun to tax calculation hyperlink")
      TaxCalculationsBreakdown.clickReturnTaxPage()
      Then("the user is navigated to the calculate SDLT due page")
      CalculateSDLTDue.verifyPageTitle(CalculateSDLTDue.leaseholdSDLTDuepageTitle)
      CalculateSDLTDue.saveAndContinue()
      Then("user is navigated to what is the SDLT self-assessment page")

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
      TaxCalculationsTotalAmountDue.saveAndContinue()
      Then("the user is navigated to the pay penalties page")

      TaxCalculationsPenalties.verifyPageTitle(
        TaxCalculationsPenalties.pageTitleLeaseholdCalculated
      )
      When("user selects yes radio button and continues")
      TaxCalculationsPenalties.radioButton(TaxCalculationsPenalties.yes)
      TaxCalculationsPenalties.saveAndContinue()
      // Navigate to check your answers page
      // User clicks on Confirm and Continue
      // User is navigated to Return to TaskList
    }

    // scenario 4 Leasehold Self assessed
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
      And("the user navigated to tax calculation")
      ReturnTaskList.clickLinkById("task-list-link-tax-calculation-questions")

      Then("the confirm effective date of the transaction page displayed")
      TaxCalculationsTotalAmountDue.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/tax-calculation/confirm-effective-date-of-transaction"
      )
      ConfirmEffectiveDate.verifyPageTitle(ConfirmEffectiveDate.pageTitle)

      ConfirmEffectiveDate.saveAndContinue()

      Then("the user is navigated to Is this effective date of transaction page")

      IsThisEffectiveDateOfTransaction.verifyPageTitle(IsThisEffectiveDateOfTransaction.pageTitle)
      When("the user selects yes radio button and continues")
      IsThisEffectiveDateOfTransaction.radioButton(IsThisEffectiveDateOfTransaction.yes)
      IsThisEffectiveDateOfTransaction.saveAndContinue()

      Then("the Freehold calculated Before you start page is displayed")
      TaxCalculationsBeforeYouStart.verifyPageTitle(TaxCalculationsBeforeYouStart.pageTitleLeaseholdNotCalculated)
      TaxCalculationsBeforeYouStart.saveAndContinue()
      Then("the user is navigated to the calculate SDLT due page")

      CalculateSDLTDue.verifyPageTitle(CalculateSDLTDue.leaseholdSelfAssesedSDLTDuepageTitle)
      When("the user click save and continue button")
      TaxCalculationsBreakdown.saveAndContinue()
      Then("the user is navigated to the total premium value page")
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
      TaxDueOnNPV.verifyPageTitle(TaxDueOnNPV.pageTitle)
      When("user enter the NPV value and click save and continue button")
      TaxDueOnNPV.input(
        By.id(TaxDueOnNPV.taxDueOnNPVAmountInput),
        TaxDueOnNPV.taxDueOnNPVAmount
      )

      TaxDueOnNPV.saveAndContinue()

      Then("user is navigated to what is the total amount due page")
      TaxCalculationsTotalAmountDue.verifyPageTitle(
        TaxCalculationsTotalAmountDue.pageTitle
      )
      When("user enter the amount value and click save and continue button")
      TaxCalculationsTotalAmountDue.input(
        By.id(TaxCalculationsTotalAmountDue.tppTax),
        TaxCalculationsTotalAmountDue.tppTaxInput
      )
      TaxCalculationsTotalAmountDue.saveAndContinue()
      Then("the user is navigated to the pay penalties page")
      TaxCalculationsPenalties.verifyPageTitle(
        TaxCalculationsPenalties.pageTitleLeaseholdNotCalculated
      )
      When("user selects no radio button and continues")
      TaxCalculationsPenalties.radioButton(TaxCalculationsPenalties.no)
      TaxCalculationsPenalties.saveAndContinue()
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
