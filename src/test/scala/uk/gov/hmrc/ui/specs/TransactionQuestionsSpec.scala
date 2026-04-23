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
import uk.gov.hmrc.selenium.webdriver.Driver.instance

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

      When("the user selects yes radio button and continues")
      DoYouKnowDateOfContractOrConclusionOfMissives.radioButton(DoYouKnowDateOfContractOrConclusionOfMissives.yes)
      DoYouKnowDateOfContractOrConclusionOfMissives.saveAndContinue()
      Then("the Enter Date of Contract page is displayed")
      EnterDateOfContract.verifyPageTitle(EnterDateOfContract.pageTitle)

      When("the user enters the date of contract")
      EnterDateOfContract.enterDateOfContract()
      EnterDateOfContract.saveAndContinue()
      Then("the Linked Transactions page is displayed")
      LinkedTransaction.verifyPageTitle(LinkedTransaction.pageTitle)

      When("the user selects Yes")
      LinkedTransaction.radioButton(LinkedTransaction.yes)
      LinkedTransaction.saveAndContinue()
      Then("the user is navigated to the Total Consideration Of Linked Transactions Page")
      TotalConsiderationOfLinkedTransaction.verifyPageTitle(TotalConsiderationOfLinkedTransaction.pageTitle)

      When("the user enters the Total Consideration Of Linked Transactions ")
      TotalConsiderationOfLinkedTransaction.input(
        By.id(TotalConsiderationOfLinkedTransaction.totalConsiderationOfLT),
        TotalConsiderationOfLinkedTransaction.totalConsiderationOfLTInput
      )
      TotalConsiderationOfLinkedTransaction.saveAndContinue()
      Then("the user is navigated to Claiming Relief page")
      ClaimingRelief.verifyPageTitle(ClaimingRelief.pageTitle)

      When("the user select Yes button and continues")
      ClaimingRelief.radioButton(ClaimingRelief.yes)
      ClaimingRelief.saveAndContinue()
      Then("user is navigated to reason for claiming relief page")
      ReasonForClaimingRelief.verifyPageTitle(ReasonForClaimingRelief.pageTitle)

      When("user selects charities radio button and continues")
      ReasonForClaimingRelief.radioButton(ReasonForClaimingRelief.charitiesRelief)
      ReasonForClaimingRelief.saveAndContinue()
      Then("user is navigated to Do you know the charity's registration number page")
      DoYouKnowCharityRegistrationNumber.verifyPageTitle(DoYouKnowCharityRegistrationNumber.pageTitle)
      DoYouKnowCharityRegistrationNumber.validateCharityComissionLink()
      DoYouKnowCharityRegistrationNumber.validateCharityComissionNorthernIrelandLink()

      And("user selects yes radio button and continues")
      DoYouKnowCharityRegistrationNumber.radioButton(DoYouKnowCharityRegistrationNumber.yes)
      DoYouKnowCharityRegistrationNumber.saveAndContinue()
      Then("user is navigated to enter charity registration number page")
      EnterCharityRegistrationNumber.verifyPageTitle(EnterCharityRegistrationNumber.pageTitle)
      When("user enters charity registration number and continues")
      EnterCharityRegistrationNumber.input(
        By.id(EnterCharityRegistrationNumber.charityRegistrationNumber),
        EnterCharityRegistrationNumber.charityRegistrationNumberInput
      )
      EnterCharityRegistrationNumber.saveAndContinue()

      Then("the user is navigated to the Partial Relief page")
      PartialRelief.verifyPageTitle(PartialRelief.pageTitle)

      When("the user select Yes button and continues")
      PartialRelief.radioButton(PartialRelief.yes)
      PartialRelief.saveAndContinue()
      Then("the user is navigated to the claim partial relief page")
      ClaimingPartialRelief.verifyPageTitle(ClaimingPartialRelief.pageTitle)

      When("the user enters the total partial relief claimed")
      ClaimingPartialRelief.input(
        By.id(ClaimingPartialRelief.totalPartialRelief),
        ClaimingPartialRelief.totalPartialReliefInput
      )
      ClaimingPartialRelief.saveAndContinue()

      /*user navigated to considerations effected by uncertain future events page
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

      When("the user selects yes radio button and continues")
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
      Then("the user is navigated to Forms of Consideration")
      FormsOfConsideration.verifyPageTitle(FormsOfConsideration.pageTitle)

      When("the user selects options")
      FormsOfConsideration.checkbox(FormsOfConsideration.cash, true)
      FormsOfConsideration.checkbox(FormsOfConsideration.building_works, true)
      FormsOfConsideration.checkbox(FormsOfConsideration.shares_quoted_company, true)
      FormsOfConsideration.checkbox(FormsOfConsideration.contingent, true)
      FormsOfConsideration.saveAndContinue()
      Then("the Linked Transactions page is displayed")
      LinkedTransaction.verifyPageTitle(LinkedTransaction.pageTitle)

      When("the user selects Yes and continues")
      LinkedTransaction.radioButton(LinkedTransaction.yes)
      LinkedTransaction.saveAndContinue()
      Then("the user is navigated to the Total Consideration Of Linked Transactions Page")
      TotalConsiderationOfLinkedTransaction.verifyPageTitle(TotalConsiderationOfLinkedTransaction.pageTitle)

      When("the user enters the Total Consideration Of Linked Transactions")
      TotalConsiderationOfLinkedTransaction.input(
        By.id(TotalConsiderationOfLinkedTransaction.totalConsiderationOfLT),
        TotalConsiderationOfLinkedTransaction.totalConsiderationOfLTInput
      )
      TotalConsiderationOfLinkedTransaction.saveAndContinue()
      Then("the user is navigated to Claiming Relief page")
      ClaimingRelief.verifyPageTitle(ClaimingRelief.pageTitle)

      When("the user select Yes button and continues")
      ClaimingRelief.radioButton(ClaimingRelief.yes)
      ClaimingRelief.saveAndContinue()
      Then("user is navigated to reason for claiming relief page")
      ReasonForClaimingRelief.verifyPageTitle(ReasonForClaimingRelief.pageTitle)

      And("user selects charities radio button and continues")
      ReasonForClaimingRelief.radioButton(ReasonForClaimingRelief.partExchange)
      // enable below step after the flow is ready
      ReasonForClaimingRelief.saveAndContinue()
      /*         user is navigated to DO you know the company CIS number page
user selects yes radio button and continues
user enters CIS number and continues */

      Then("the user is navigated to the Partial Relief page")
// remove below line when navigation completed
      PartialRelief.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/about-the-transaction/partial-relief"
      )
      PartialRelief.verifyPageTitle(PartialRelief.pageTitle)

      When("the user select Yes button and continues")
      PartialRelief.radioButton(PartialRelief.yes)
      PartialRelief.saveAndContinue()
      Then("the user is navigated to the claim partial relief page")
      ClaimingPartialRelief.verifyPageTitle(ClaimingPartialRelief.pageTitle)

      When("the user enters the total partial relief claimed")
      ClaimingPartialRelief.input(
        By.id(ClaimingPartialRelief.totalPartialRelief),
        ClaimingPartialRelief.totalPartialReliefInput
      )
      ClaimingPartialRelief.saveAndContinue()
      /*user navigated to considerations effected by uncertain future events page
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
