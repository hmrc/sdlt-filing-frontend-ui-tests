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

import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.verbs.ShouldVerb
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, GivenWhenThen}
import uk.gov.hmrc.selenium.webdriver.{Browser, ScreenshotOnFailure}
import uk.gov.hmrc.ui.pages.*
import uk.gov.hmrc.ui.pages.Transaction.*
import uk.gov.hmrc.ui.pages.Preliminary.TransactionType
import uk.gov.hmrc.ui.tags.*
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation

class TransactionQuestionsSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("SDLT Filing Frontend About The Transaction Questions") {

    Scenario(
      "Complete the Transactions Questions user journey with grant of lease transaction type and reason for claiming relief is selected as charities",
      TransactionJourney
    ) {

      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("firstJson"))

      When("the user clicks on the 'Transaction Questions' link")
      ReturnTaskList.clickLinkById("task-list-link-transaction-questions")
      Then("the user is navigated to the Before you start page")
      TransactionBeforeYouStart.verifyPageTitle(TransactionBeforeYouStart.pageTitle)

      When("the user clicks the Continue button")
      TransactionBeforeYouStart.saveAndContinue()
      Then("user is navigated to confirm type of transaction page")
      ConfirmTheTransaction.verifyPageTitle(ConfirmTheTransaction.pageTitle)

      When("user selects no radio button and continues")
      ConfirmTheTransaction.radioButton(ConfirmTheTransaction.no)
      ConfirmTheTransaction.saveAndContinue()
      Then("the Transaction Type page is displayed")
      TransactionType.verifyPageTitle(TransactionType.pageTitleTransaction)

      When("the user selects Grant of Lease as the transaction type")
      TransactionType.radioButton(TransactionType.grantOfLease)
      // Uncomment when navigation to next page is ready
      // TransactionType.saveAndContinue()

      Then("the Effective Date of Transaction page is displayed")
      EffectiveDateOfTransaction.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/about-the-transaction/effective-date-of-transaction"
      )
      EffectiveDateOfTransaction.verifyPageTitle(EffectiveDateOfTransaction.pageTitle)

      When("the user enters the effective date of transaction")
      EffectiveDateOfTransaction.enterEffectiveDateOfTransaction()
      // Uncomment when next page is ready
      // EffectiveDateOfTransaction.saveAndContinue()

      /*
    user is navigated to Effective date of transaction page
    user enters effective date of transaction and continues */
      Then(" user is navigated to Do you Know Date of contract or conclusion of missives page")
      // remove below line once navigation is ready
      DoYouKnowDateOfContractOrConclusionOfMissives.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/about-the-transaction/add-date-of-contract"
      )
      DoYouKnowDateOfContractOrConclusionOfMissives.verifyPageTitle(
        DoYouKnowDateOfContractOrConclusionOfMissives.pageTitle
      )
      And("User selects yes radio button and continues")
      DoYouKnowDateOfContractOrConclusionOfMissives.radioButton(DoYouKnowDateOfContractOrConclusionOfMissives.yes)
      DoYouKnowDateOfContractOrConclusionOfMissives.saveAndContinue()

      /*      user enters date of contract or conclusion of missives and continues
      user navigates to Linked transactions page
      user selects yes radio button and continues
    user navigates to total consideration of all linked transactions page
      user enters the amount and continues
      user is navigated to claiming relief page
    user selects yes radio button and continues
    user navigates to reason for claiming relief page
          user selects charities radio button and continues
          user is navigated to DO you know the charity 's registration number page
      user selects yes radio button and continues
    user enters charity registration number and continues
    user is navigated to Partial relief page
    user selects yes radio button and continues
    user is navigated to claiming partial relief page
      user enters the claimed amount and continues
    user navigated to considerations effected by uncertain future events page
      user selects yes radio button and continues
    user is navigated to deferring payment page
    user selects yes radio button and continues
    user is navigated to the use of land or property page(Only if property type is selected as non - residential or mixed )
    user selects checkboxes and continues
    user is navigated to Sale of business page
      user selects yes radio button and continues
    user is navigated to what is included in sale of business page
      user selects checkboxes and continues(select others option checkbox)
    user is navigated to total consideration of all assets included in sale of business page
    user enters the amount and continues
      user is navigated to the CAP1 or NSBC page
    user selects yes radio button and continues
    user is navigated to restrictions convenants and conditions page
      user selects yes radio button and continues
    user is navigated to enter details of restrictions convenants and conditions page
      user enters the details and continues
      user is navigated to exchange or part exchange of land or property page
    user selects yes radio button and continues
    user is navigated to address flow and fills the details
      user navigates to Exercising an option page
    user selects yes radio button and continues
    user is navigated to CYA page and verifies the answers and submits
       */

    }

    Scenario(
      "Complete the Transactions Questions user journey with  transaction type other than grant of lease and claiming relief is selected as part exchange flow ",
      TransactionJourney
    ) {

      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("firstJson"))

      When("the user clicks on the 'Transaction Questions' link")
      ReturnTaskList.clickLinkById("task-list-link-transaction-questions")
      Then("the user is navigated to the Before you start page")
      TransactionBeforeYouStart.verifyPageTitle(TransactionBeforeYouStart.pageTitle)

      When("the user clicks the Continue button")
      TransactionBeforeYouStart.saveAndContinue()
      Then("user is navigated to confirm type of transaction page")
      ConfirmTheTransaction.verifyPageTitle(ConfirmTheTransaction.pageTitle)

      When("user selects no radio button and continues")
      ConfirmTheTransaction.radioButton(ConfirmTheTransaction.no)
      ConfirmTheTransaction.saveAndContinue()
      Then("the Transaction Type page is displayed")
      TransactionType.verifyPageTitle(TransactionType.pageTitleTransaction)

      When("the user selects Conveyance as the transaction type")
      TransactionType.radioButton(TransactionType.conveyance)
      // Uncomment when navigation to next page is ready
      // TransactionType.saveAndContinue()

      // user is navigated to type oftransaction page
      // user selects Compensation trasfer or others radio button and continues (LorA to F or O)

      Then("the Effective Date of Transaction page is displayed")
      EffectiveDateOfTransaction.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/about-the-transaction/effective-date-of-transaction"
      )
      EffectiveDateOfTransaction.verifyPageTitle(EffectiveDateOfTransaction.pageTitle)

      When("the user enters the effective date of transaction")
      EffectiveDateOfTransaction.enterEffectiveDateOfTransaction()
      // Uncomment when next page is ready
      // EffectiveDateOfTransaction.saveAndContinue()

      /*  user is navigated to type of transaction page
        user selects Compensation trasfer or others  radio button and continues(LorA to F or O)
      user is navigated to Effective date of transaction page
      user enters effective date of transaction and continues */

      Then(" user is navigated to Do you Know Date of contract or conclusion of missives page")
      // remove below line once navigation is ready
      DoYouKnowDateOfContractOrConclusionOfMissives.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/about-the-transaction/add-date-of-contract"
      )
      DoYouKnowDateOfContractOrConclusionOfMissives.verifyPageTitle(
        DoYouKnowDateOfContractOrConclusionOfMissives.pageTitle
      )
      And("User selects yes radio button and continues")
      DoYouKnowDateOfContractOrConclusionOfMissives.radioButton(DoYouKnowDateOfContractOrConclusionOfMissives.yes)
      DoYouKnowDateOfContractOrConclusionOfMissives.saveAndContinue()

      /* user enters date of contract or conclusion of missives and continues
      user navigated to total consideration of transaction page
        user enters total consideration of transaction amount and continues
      user is navigated to Is VAT included in the total consideration ? page
      user selects yes radio button and continues
      user is navigated ro enter VAT amount page
        user enters VAT amount and continues
        user is navigated to forms of consideration page
        user selects check boxes and continues
        user navigates to Linked transactions page
        user selects yes radio button and continues
      user navigates to total consideration of all linked transactions page
        user enters the amount and continues
        user is navigated to claiming relief page
      user selects yes radio button and continues
      user navigates to reasonfor claiming relief page
            user selects part exchange radio button and continues
            user is navigated to DO you know the company CIS number page
        user selects yes radio button and continues
      user enters CIS number and continues
      user is navigated to Partial relief page
      user selects yes radio button and continues
      user is navigated to claiming partial relief page
        user enters the claimed amount and continues
      user navigated to considerations effected by uncertain future events page
        user selects yes radio button and continues
      user is navigated to deferring payment page
      user selects yes radio button and continues
      user is navigated to the use of land or property page(Only if property type is selected as non - residential or mixed )
      user selects checkboxes and continues
      user is navigated to Sale of business page
        user selects yes radio button and continues
      user is navigated to what is included in sale of business page
        user selects checkboxes and continues(select others option checkbox)
      user is navigated to total consideration of all assets included in sale of business page
      user enters the amount and continues
        user is navigated to the CAP1 or NSBC page
      user selects yes radio button and continues
      user is navigated to restrictions convenants and conditions page
      user selects yes radio button and continues
      user is navigated to enter details of restrictions convenants and conditions page
        user enters the details and continues
        user is navigated to exchange or part exchange of land or property page
      user selects yes radio button and continues
      user is navigated to address flow and fills the details
        user navigates to Exercising an option page
      user selects yes radio button and continues
      user is navigated to CYA page and verifies the answers and submits*/
    }
  }
}
