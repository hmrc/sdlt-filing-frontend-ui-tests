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
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("prelimTransactionF"))

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

      When("the user selects Grant of Lease as the transaction type and continues")
      TransactionType.radioButton(TransactionType.grantOfLease)
      TransactionType.saveAndContinue()
      Then("the user navigates to confirming the change of transaction type page")
      ConfirmChangeOfTransaction.verifyPageTitle(ConfirmChangeOfTransaction.pageTitle)
      When("the user selects yes radio button and continues")
      ConfirmChangeOfTransaction.radioButton(ConfirmChangeOfTransaction.yes)
      ConfirmChangeOfTransaction.saveAndContinue()

      Then("the Effective Date of Transaction page is displayed")
      EffectiveDateOfTransaction.verifyPageTitle(EffectiveDateOfTransaction.pageTitle)
      When("the user enters the effective date of transaction and continues")
      EffectiveDateOfTransaction.enterEffectiveDateOfTransaction()
      EffectiveDateOfTransaction.saveAndContinue()

      Then(" user is navigated to Do you Know Date of contract or conclusion of missives page")
      DoYouKnowDateOfContractOrConclusionOfMissives.verifyPageTitle(
        DoYouKnowDateOfContractOrConclusionOfMissives.pageTitle
      )
      And("User selects yes radio button and continues")
      DoYouKnowDateOfContractOrConclusionOfMissives.radioButton(DoYouKnowDateOfContractOrConclusionOfMissives.yes)
      DoYouKnowDateOfContractOrConclusionOfMissives.saveAndContinue()

      Then("the Enter Date of Contract page is displayed")
      EnterDateOfContract.verifyPageTitle(EnterDateOfContract.pageTitle)

      When("the user enters the date of contract")
      EnterDateOfContract.enterDateOfContract()
      // Uncomment when navigation to next page is ready
      // EnterDateOfContract.saveAndContinue()

      /*
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
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("prelimTransactionL"))

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
      TransactionType.saveAndContinue()
      Then("the user navigates to confirming the change of transaction type page")
      ConfirmChangeOfTransaction.verifyPageTitle(ConfirmChangeOfTransaction.pageTitle)
      When("the user selects yes radio button and continues")
      ConfirmChangeOfTransaction.radioButton(ConfirmChangeOfTransaction.yes)
      ConfirmChangeOfTransaction.saveAndContinue()

      Then("the Effective Date of Transaction page is displayed")
      EffectiveDateOfTransaction.verifyPageTitle(EffectiveDateOfTransaction.pageTitle)
      When("the user enters the effective date of transaction and continues")
      EffectiveDateOfTransaction.enterEffectiveDateOfTransaction()
      EffectiveDateOfTransaction.saveAndContinue()

      Then(" user is navigated to Do you Know Date of contract or conclusion of missives page")
      DoYouKnowDateOfContractOrConclusionOfMissives.verifyPageTitle(
        DoYouKnowDateOfContractOrConclusionOfMissives.pageTitle
      )
      And("User selects yes radio button and continues")
      DoYouKnowDateOfContractOrConclusionOfMissives.radioButton(DoYouKnowDateOfContractOrConclusionOfMissives.yes)
      DoYouKnowDateOfContractOrConclusionOfMissives.saveAndContinue()

      Then("the Enter Date of Contract page is displayed")
      EnterDateOfContract.verifyPageTitle(EnterDateOfContract.pageTitle)

      When("the user enters the date of contract")
      EnterDateOfContract.enterDateOfContract()
      EnterDateOfContract.saveAndContinue()

      Then("the Total Consideration Of Transaction page is displayed")
      TotalConsiderationOfTransaction.verifyPageTitle(TotalConsiderationOfTransaction.pageTitle)

      When("the user enters the total consideration")
      TotalConsiderationOfTransaction.input(
        By.id(TotalConsiderationOfTransaction.totalConsideration),
        TotalConsiderationOfTransaction.totalConsiderationInput
      )
      TotalConsiderationOfTransaction.saveAndContinue()

      Then("the user is navigated to Is VAT included in the total consideration page")
      IsVatIncludedInTotalConsideration.verifyPageTitle(IsVatIncludedInTotalConsideration.pageTitle)

      When("the user selects yes radio button and continues")
      IsVatIncludedInTotalConsideration.radioButton(IsVatIncludedInTotalConsideration.yes)
      IsVatIncludedInTotalConsideration.saveAndContinue()

      Then("the user is navigated to the Amount of VAT page")
      AmountOfVAT.verifyPageTitle(AmountOfVAT.pageTitle)

      When("the user enters the Amount Of VAT")
      AmountOfVAT.input(
        By.id(AmountOfVAT.totalAmountOfVAT),
        AmountOfVAT.totalAmountOfVATInput
      )
      AmountOfVAT.saveAndContinue()

      /* user is navigated to forms of consideration page
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
