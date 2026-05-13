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
import uk.gov.hmrc.selenium.webdriver.{Browser, ScreenshotOnFailure}
import uk.gov.hmrc.ui.pages.*
import uk.gov.hmrc.ui.pages.TaxCalculations.*
import uk.gov.hmrc.ui.pages.Preliminary.PreliminaryBeforeYouStart
import uk.gov.hmrc.ui.tags.*
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
      ReturnTaskList.clickLinkById("task-list-link-tax-calculation-questions")
      Then("the Freehold calculated Before you start page is displayed")
      TaxCalculationsBeforeYouStart.verifyPageTitle(TaxCalculationsBeforeYouStart.pageTitle)
      /* scenario 1 user select self assessment date
      / user is navigated to Tax cal summary page*/
      // user click check your SDLT breakdown and continue
      When("the user want the breakdown page journey")
      Then("the user is navigated to the SDLT breakdown page")
      TaxCalculationsBreakdown.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/tax-calculation/freehold-calculated/SDLT-breakdown"
      )
      TaxCalculationsBreakdown.verifyPageTitle(TaxCalculationsBreakdown.pageTitle)
      When("the user want to go return to the tax calculation page")
      TaxCalculationsBreakdown.clickReturnTaxPage()
      Then("the Freehold calculated Before you start page is displayed")
      TaxCalculationsBeforeYouStart.verifyPageTitle(TaxCalculationsBeforeYouStart.pageTitle)

      // user is navigated to does the amount you intend to pay include penalties and interest charges radio button page
      Then("the user is navigated to the pay penalties page")
      TaxCalculationsPenalties.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/tax-calculation/freehold-calculated/are-penalties-and-interest-included"
      )
      TaxCalculationsPenalties.verifyPageTitle(TaxCalculationsPenalties.pageTitle)
      When("user selects no radio button and continues")
      TaxCalculationsPenalties.radioButton(TaxCalculationsPenalties.no)
      // TaxCalculationsPenalties.saveAndContinue()

      /*
    user is navigated to Total amount due pag
    user enter amount to be retunred and continue*/
      Then("user is navigated to what is the total amount due page")
      TaxCalculationsTotalPremiumValueFreeholdTaxCalulation.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/tax-calculation/freehold-calculated/total-amount-due"
      )
      TaxCalculationsTotalPremiumValueFreeholdTaxCalulation.verifyPageTitle(
        TaxCalculationsTotalPremiumValueFreeholdTaxCalulation.pageTitle
      )
      When("user enter the amount value and click save and continue button")
      TaxCalculationsTotalPremiumValueFreeholdTaxCalulation.input(
        By.id(TaxCalculationsTotalPremiumValueFreeholdTaxCalulation.tppTax),
        TaxCalculationsTotalPremiumValueFreeholdTaxCalulation.tppTaxInput
      )
      TaxCalculationsTotalPremiumValueFreeholdTaxCalulation.saveAndContinue()
      Then("the Preliminary page is shown")
      PreliminaryBeforeYouStart.verifyPageTitle(PreliminaryBeforeYouStart.pageTitle)
      /*

    user is navigated to Tax cal summary page
    user click continue button in Tax cal summary page
    user is navigated to the Tax calculation SDLT self assessment page
    user enter self assessment amount of SDLT and click continue
    user is navigated to does the amount you intend to pay include penalties and interest charges radio button page
    user selects yes radio button and continues
    user is navigated to Tax calculation Check your answers page
    user select confirm and continue button
    user is navigated to tasklist
       */
    }

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

      /* scenario 2 Tax calculation - Freehold not calculated - Stamp Taxes online*/
      When("the user starts the tax calculations journey")
      ReturnTaskList.clickLinkById("task-list-link-tax-calculation-questions")

      Then("the Freehold not calculated Before you start page is displayed")
      TaxCalculationsBeforeYouStart.verifyPageTitle(TaxCalculationsBeforeYouStart.pageTitleFreeholdNotCalculated)

      Then("the user is navigated to the pay penalties page")
      TaxCalculationsPenaltiesFreeholdselfassesed.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/tax-calculation/freehold-not-calculated/are-penalties-and-interest-included"
      )
      TaxCalculationsPenaltiesFreeholdselfassesed.verifyPageTitle(TaxCalculationsPenaltiesFreeholdselfassesed.pageTitle)

      When("user selects yes radio button and continues")
      TaxCalculationsPenaltiesFreeholdselfassesed.radioButton(TaxCalculationsPenaltiesFreeholdselfassesed.yes)
      TaxCalculationsPenaltiesFreeholdselfassesed.saveAndContinue()
      Then("the Preliminary page is shown")
      PreliminaryBeforeYouStart.verifyPageTitle(PreliminaryBeforeYouStart.pageTitle)
    }
    /* Scenario 3 Tax calculation - Leasehold calculated - Stamp Taxes online*/
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
      ReturnTaskList.clickLinkById("task-list-link-tax-calculation-questions")
      Then("the Freehold calculated Before you start page is displayed")
      TaxCalculationsBeforeYouStart.verifyPageTitle(TaxCalculationsBeforeYouStart.pageTitleLeaseholdCalculated)

      // Navigate to tax due on NPV and click continue

      Then("user is navigated to what is the total amount due page")
      TaxCalculationsTotalPremiumValueLeasehold.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/tax-calculation/leasehold-calculated/total-amount-due"
      )
      TaxCalculationsTotalPremiumValueLeasehold.verifyPageTitle(
        TaxCalculationsTotalPremiumValueLeasehold.pageTitle
      )
      When("user enter the amount value and click save and continue button")
      TaxCalculationsTotalPremiumValueLeasehold.input(
        By.id(TaxCalculationsTotalPremiumValueLeasehold.tppTax),
        TaxCalculationsTotalPremiumValueLeasehold.tppTaxInput
      )
      // TaxCalculationsTotalPremiumValueLeasehold.saveAndContinue()
      // Navigate to total amount due page and click continue to open pay penalities page
      Then("the user is navigated to the pay penalties page")
      TaxCalculationsPenaltiesLeaseholdCalculated.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/tax-calculation/leasehold-calculated/are-penalties-and-interest-included"
      )
      TaxCalculationsPenaltiesLeaseholdCalculated.verifyPageTitle(
        TaxCalculationsPenaltiesLeaseholdCalculated.pageTitle
      )
      When("user selects no radio button and continues")
      TaxCalculationsPenaltiesLeaseholdCalculated.radioButton(TaxCalculationsPenaltiesLeaseholdCalculated.no)
      // TaxCalculationsPenaltiesLeaseholdCalculated.saveAndContinue()
      // Navigate to check your answers page
    }
    /* Scenario 2 (nolease involved)
    user is navigated to HMRC cannot calculate the SDLT due page
    user click continue button
    user enter self assessment amount of SDLT and click continue
    user is navigated to Total amount due page
    user enter amount to be retunred and continue
    user selects yes radio button and continues
    user is navigated to Tax calculation Check your answers page
    user select confirm and continue button
    user is navigated to tasklist
     */

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
      ReturnTaskList.clickLinkById("task-list-link-tax-calculation-questions")
      Then("the Freehold calculated Before you start page is displayed")
      TaxCalculationsBeforeYouStart.verifyPageTitle(TaxCalculationsBeforeYouStart.pageTitleLeaseholdNotCalculated)

// user enter the total amount you intend to pay with this return and click save and continue
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
      TaxCalculationsTotalAmountDueLeaseholdSelfassesed.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/tax-calculation/leasehold-not-calculated/total-amount-due"
      )
      TaxCalculationsTotalAmountDueLeaseholdSelfassesed.verifyPageTitle(
        TaxCalculationsTotalAmountDueLeaseholdSelfassesed.pageTitle
      )
      When("user enter the amount value and click save and continue button")
      TaxCalculationsTotalAmountDueLeaseholdSelfassesed.input(
        By.id(TaxCalculationsTotalAmountDueLeaseholdSelfassesed.tppTax),
        TaxCalculationsTotalAmountDueLeaseholdSelfassesed.tppTaxInput
      )
      // TaxCalculationsTotalAmountDueLeaseholdSelfassesed.saveAndContinue()
      // user is navigated to does the amount you intend to pay include penalties and interest charges radio button page
      // user selects yes radio button and continues

      /*Scenario 4
    user is navigated to total mount due page
    user is navigated to does the amount you intend to pay include penalties and interest charges radio button page
    user selects yes radio button and continues
    user is navigated to Tax calculation Check your answers page
    user select confirm and continue button
    user is navigated to tasklist
       */
    }
  }
}
