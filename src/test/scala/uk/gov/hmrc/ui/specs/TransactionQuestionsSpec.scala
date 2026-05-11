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
      Then("user is navigated to Do you Know Date of contract or conclusion of missives page")
      DoYouKnowDateOfContractOrConclusionOfMissives.verifyPageTitle(
        DoYouKnowDateOfContractOrConclusionOfMissives.pageTitle
      )

      When("the user selects no radio button and continues")
      DoYouKnowDateOfContractOrConclusionOfMissives.radioButton(DoYouKnowDateOfContractOrConclusionOfMissives.no)
      DoYouKnowDateOfContractOrConclusionOfMissives.saveAndContinue()
      Then("the Linked Transactions page is displayed")
      LinkedTransaction.verifyPageTitle(LinkedTransaction.pageTitle)

      When("the user selects No")
      LinkedTransaction.radioButton(LinkedTransaction.no)
      LinkedTransaction.saveAndContinue()
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
//      DoYouKnowCharityRegistrationNumber.validateCharityComissionLink()
//      DoYouKnowCharityRegistrationNumber.validateCharityComissionNorthernIrelandLink()

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

      Then("the considerations effected by uncertain future events page is shown")
      ConsiderationsAffectedByUncertainFutureEvents.verifyPageTitle(
        ConsiderationsAffectedByUncertainFutureEvents.pageTitle
      )
      And("user selects yes radio button and continues")
      ConsiderationsAffectedByUncertainFutureEvents.radioButton(ConsiderationsAffectedByUncertainFutureEvents.yes)
      ConsiderationsAffectedByUncertainFutureEvents.saveAndContinue()
      Then("the DeferringPayment page is shown")
      DeferringPayment.verifyPageTitle(DeferringPayment.pageTitle)

      When("the user confirms the purchaser is applying for a deferment")
      DeferringPayment.radioButton(DeferringPayment.yes)
      DeferringPayment.saveAndContinue()
      Then(
        "user is navigated to the use of land or property page(Only if property type is selected as non - residential or mixed )"
      )
      WhatIsThePropertyUsedFor.verifyPageTitle(WhatIsThePropertyUsedFor.pageTitle)
      And("user selects checkboxes and continues")
      WhatIsThePropertyUsedFor.checkbox(WhatIsThePropertyUsedFor.office, true)
      WhatIsThePropertyUsedFor.checkbox(WhatIsThePropertyUsedFor.hotel, true)
      WhatIsThePropertyUsedFor.saveAndContinue()

      Then("the SaleOfABusiness page is shown")
      SaleOfABusiness.verifyPageTitle(SaleOfABusiness.pageTitle)
      When("the user confirms the transaction is part of the sale of a business")
      SaleOfABusiness.radioButton(SaleOfABusiness.yes)
      SaleOfABusiness.saveAndContinue()

      Then("user provides what is included in sale of business")
      WhatIncludedInSale.verifyPageTitle(WhatIncludedInSale.pageTitle)

      When("user selects checkboxes and continues")
      WhatIncludedInSale.checkbox(WhatIncludedInSale.stock, true)
      WhatIncludedInSale.checkbox(WhatIncludedInSale.chattelsAndMovables, true)
      WhatIncludedInSale.saveAndContinue()
      Then("user is navigated to the Total Consideration of all assets page")
      TotalConsiderationOfAllAssets.verifyPageTitle(TotalConsiderationOfAllAssets.pageTitle)

      When("the user enters the amount and click continue")
      TotalConsiderationOfAllAssets.input(
        By.id(TotalConsiderationOfAllAssets.totalConsiderationOfAllAssets),
        TotalConsiderationOfAllAssets.totalConsiderationOfAllAssetsInput
      )
      TotalConsiderationOfAllAssets.saveAndContinue()
      Then("the user is navigated to the applied for CAP1 or NSBC page")
      AppliedForCAP1OrNSBC.verifyPageTitle(AppliedForCAP1OrNSBC.pageTitle)

      When("the user selects yes radio button and continues")
      AppliedForCAP1OrNSBC.radioButton(AppliedForCAP1OrNSBC.yes)
      AppliedForCAP1OrNSBC.saveAndContinue()

      Then("the user is navigated to the have you followed the ruling under CAP1 or NSBC page")
      HaveYouFollowedTheRuling.verifyPageTitle(
        HaveYouFollowedTheRuling.pageTitle
      )

      When("the user selects yes radio button and continues")
      HaveYouFollowedTheRuling.radioButton(HaveYouFollowedTheRuling.yes)
      HaveYouFollowedTheRuling.saveAndContinue()
      Then("the user is navigated to Restrictions Covenants or Conditions Page")
      RestrictionsConvenantsOrConditions.verifyPageTitle(RestrictionsConvenantsOrConditions.pageTitle)

      When("the user selects yes radio button and continues")
      RestrictionsConvenantsOrConditions.radioButton(RestrictionsConvenantsOrConditions.yes)
      RestrictionsConvenantsOrConditions.saveAndContinue()
      Then("the user is navigated to enter details of restrictions convenants and conditions page")
      DescriptionOfRestrictionsConvenantsOrConditions.verifyPageTitle(
        DescriptionOfRestrictionsConvenantsOrConditions.pageTitle
      )

      When("the user enter the details of restrictions and continue")
      DescriptionOfRestrictionsConvenantsOrConditions.input(
        By.id(DescriptionOfRestrictionsConvenantsOrConditions.restrictionsConvenantsOrConditions),
        DescriptionOfRestrictionsConvenantsOrConditions.restrictionsConvenantsOrConditionsInput
      )
      DescriptionOfRestrictionsConvenantsOrConditions.saveAndContinue()
      Then("the ExchangeOrPartExchange page is shown")
      ExchangeOrPartExchange.verifyPageTitle(ExchangeOrPartExchange.pageTitle)

      When("the user confirms the land is being exchanged or part exchanged")
      ExchangeOrPartExchange.radioButton(ExchangeOrPartExchange.yes)
      ExchangeOrPartExchange.saveAndContinue()
      Then("user is navigated to address flow and fills the details")
      TransactionExchangeAddress.clickAddressManually()
      TransactionExchangeAddress.verifyPageTitle(TransactionExchangeAddress.editPageTitle)
      TransactionExchangeAddress.enterAddressManually("523", "AGC", "TE11 1TS")
      And("user is navigated to confirm the address page and continues")
      TransactionExchangeAddress.verifyPageTitle(TransactionExchangeAddress.confirmPageTitle)
      TransactionExchangeAddress.clickSubmitButton()
      Then("user navigates to Exercising an option page")
      ExercisingAnOption.verifyPageTitle(ExercisingAnOption.pageTitle)
      And("user selects yes radio button and continues")
      ExercisingAnOption.radioButton(ExercisingAnOption.yes)
      ExercisingAnOption.saveAndContinue()
      Then("the TransactionCheckYourAnswers page is shown")
      TransactionCheckYourAnswers.verifyPageTitle(TransactionCheckYourAnswers.pageTitle)

      When("the user updates the type of transaction")
      TransactionCheckYourAnswers.clickTypeOfTransaction()
      TransactionType.verifyPageTitle(TransactionType.pageTitleTransaction)
      TransactionType.radioButton(TransactionType.conveyance)
      TransactionType.saveAndContinue()
      Then("the TotalConsiderationOfTransaction page is shown")
      TotalConsiderationOfTransaction.verifyPageTitle(TotalConsiderationOfTransaction.pageTitle)

      When("the user provides the total consideration")
      TotalConsiderationOfTransaction.input(
        By.id(TotalConsiderationOfTransaction.totalConsideration),
        TotalConsiderationOfTransaction.totalConsiderationInput
      )
      TotalConsiderationOfTransaction.saveAndContinue()
      Then("the IsVATIncluded page is shown")
      IsVatIncludedInTotalConsideration.verifyPageTitle(IsVatIncludedInTotalConsideration.pageTitle)

      When("the user confirms there is vat included in the total consideration")
      IsVatIncludedInTotalConsideration.radioButton(IsVatIncludedInTotalConsideration.yes)
      IsVatIncludedInTotalConsideration.saveAndContinue()
      Then("the VATAmount page is shown")
      AmountOfVAT.verifyPageTitle(AmountOfVAT.pageTitle)

      When("the user provides the VAT Amount")
      AmountOfVAT.input(
        By.id(AmountOfVAT.totalAmountOfVAT),
        AmountOfVAT.totalAmountOfVATInput
      )
      AmountOfVAT.saveAndContinue()
      Then("the FormsOfConsideration page is shown")
      FormsOfConsideration.verifyPageTitle(FormsOfConsideration.pageTitle)

      When("the user provides all forms the consideration takes")
      FormsOfConsideration.checkbox(FormsOfConsideration.cash, true)
      FormsOfConsideration.checkbox(FormsOfConsideration.building_works, true)
      FormsOfConsideration.saveAndContinue()
      Then("the TransactionCheckYourAnswers page is shown")
      TransactionCheckYourAnswers.verifyPageTitle(TransactionCheckYourAnswers.pageTitle)

      When("the user updates the effective date of transaction")
      TransactionCheckYourAnswers.clickEffectiveDateOfTransaction()
      EffectiveDateOfTransaction.enterEffectiveDateOfTransactionCYA()
      EffectiveDateOfTransaction.saveAndContinue()
      Then("the TransactionCheckYourAnswers page is shown")
      TransactionCheckYourAnswers.verifyPageTitle(TransactionCheckYourAnswers.pageTitle)

      When("the user updates their answer to provide a date of contact")
      TransactionCheckYourAnswers.clickAddDateOfContract()
      DoYouKnowDateOfContractOrConclusionOfMissives.radioButton(DoYouKnowDateOfContractOrConclusionOfMissives.yes)
      DoYouKnowDateOfContractOrConclusionOfMissives.saveAndContinue()
      EnterDateOfContract.verifyPageTitle(EnterDateOfContract.pageTitle)
      EnterDateOfContract.enterDateOfContract()
      EnterDateOfContract.saveAndContinue()
      Then("the TransactionCheckYourAnswers page is shown")
      TransactionCheckYourAnswers.verifyPageTitle(TransactionCheckYourAnswers.pageTitle)

      When("the user updates their answer to confirm the transaction is linked")
      TransactionCheckYourAnswers.clickLinkedTransactions()
      LinkedTransaction.verifyPageTitle(LinkedTransaction.pageTitle)
      LinkedTransaction.radioButton(LinkedTransaction.yes)
      LinkedTransaction.saveAndContinue()
      TotalConsiderationOfLinkedTransaction.verifyPageTitle(TotalConsiderationOfLinkedTransaction.pageTitle)
      TotalConsiderationOfLinkedTransaction.input(
        By.id(TotalConsiderationOfLinkedTransaction.totalConsiderationOfLT),
        TotalConsiderationOfLinkedTransaction.totalConsiderationOfLTInput
      )
      TotalConsiderationOfLinkedTransaction.saveAndContinue()
      Then("the TransactionCheckYourAnswers page is shown")
      TransactionCheckYourAnswers.verifyPageTitle(TransactionCheckYourAnswers.pageTitle)

      When("the user confirms the purchaser is eligible to claim relief")
      TransactionCheckYourAnswers.clickClaimingRelief()
      ClaimingRelief.verifyPageTitle(ClaimingRelief.pageTitle)
      ClaimingRelief.radioButton(ClaimingRelief.yes)
      ClaimingRelief.saveAndContinue()
      Then("the TransactionCheckYourAnswers page is shown")
      TransactionCheckYourAnswers.verifyPageTitle(TransactionCheckYourAnswers.pageTitle)

      When("the user updates their answer to confirm charities as reason for claiming relief")
      TransactionCheckYourAnswers.clickReasonForRelief()
      ReasonForClaimingRelief.verifyPageTitle(ReasonForClaimingRelief.pageTitle)
      ReasonForClaimingRelief.radioButton(ReasonForClaimingRelief.charitiesRelief)
      ReasonForClaimingRelief.saveAndContinue()

      Then("the TransactionCheckYourAnswers page is shown")
      TransactionCheckYourAnswers.verifyPageTitle(TransactionCheckYourAnswers.pageTitle)

      When("the user updates their Registered Charity Number")
      TransactionCheckYourAnswers.clickEnterRegisteredCharityNumber()
      EnterCharityRegistrationNumber.verifyPageTitle(EnterCharityRegistrationNumber.pageTitle)

      When("the user provides the charity's registration number")
      EnterCharityRegistrationNumber.input(
        By.id(EnterCharityRegistrationNumber.charityRegistrationNumber),
        EnterCharityRegistrationNumber.charityRegistrationNumberInput
      )
      EnterCharityRegistrationNumber.saveAndContinue()
      Then("the TransactionCheckYourAnswers page is shown")
      TransactionCheckYourAnswers.verifyPageTitle(TransactionCheckYourAnswers.pageTitle)

      When("the user updates their answer to confirm the purchaser is claiming relief")
      TransactionCheckYourAnswers.clickPartialRelief()
      PartialRelief.verifyPageTitle(PartialRelief.pageTitle)
      PartialRelief.radioButton(PartialRelief.yes)
      PartialRelief.saveAndContinue()
      Then("the TransactionCheckYourAnswers page is shown")
      TransactionCheckYourAnswers.verifyPageTitle(TransactionCheckYourAnswers.pageTitle)

      When("the user updates the total partial relief claimed")
      TransactionCheckYourAnswers.clickClaimingPartialRelief()
      ClaimingPartialRelief.verifyPageTitle(ClaimingPartialRelief.pageTitle)
      ClaimingPartialRelief.input(
        By.id(ClaimingPartialRelief.totalPartialRelief),
        ClaimingPartialRelief.totalPartialReliefInput
      )
      ClaimingPartialRelief.saveAndContinue()
      Then("the TransactionCheckYourAnswers page is shown")
      TransactionCheckYourAnswers.verifyPageTitle(TransactionCheckYourAnswers.pageTitle)

      When("the user submits the transaction questions")
      TransactionCheckYourAnswers.saveAndContinue()
      Then("the ReturnTaskList page is shown")
      ReturnTaskList.verifyPageTitle(ReturnTaskList.pageTitle)
    }

    Scenario(
      "Complete the Transactions Questions user journey with  transaction type other than grant of lease and claiming relief is selected as part exchange flow ",
      TransactionJourney
    ) {

      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("prelimTransactionL-property-type-mixed"))

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
      ReasonForClaimingRelief.saveAndContinue()
      Then(" user is navigated to DO you know the company CIS number page")
      ConstructionIndustryScheme.verifyPageTitle(ConstructionIndustryScheme.pageTitle)
      And("user selects yes radio button and continues")
      ConstructionIndustryScheme.radioButton(ConstructionIndustryScheme.yes)
      ConstructionIndustryScheme.saveAndContinue()
      Then("user navigates to enter CIS Number page")
      EnterCISRegistrationNumber.verifyPageTitle(EnterCISRegistrationNumber.pageTitle)
      When("the user enters the enter CIS Number Page")
      EnterCISRegistrationNumber.input(
        By.id(EnterCISRegistrationNumber.CISRegistrationNumber),
        EnterCISRegistrationNumber.CISRegistrationNumberInput
      )
      EnterCISRegistrationNumber.saveAndContinue()

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
      Then("the considerations effected by uncertain future events page is shown")
      ConsiderationsAffectedByUncertainFutureEvents.verifyPageTitle(
        ConsiderationsAffectedByUncertainFutureEvents.pageTitle
      )
      And("user selects yes radio button and continues")
      ConsiderationsAffectedByUncertainFutureEvents.radioButton(ConsiderationsAffectedByUncertainFutureEvents.yes)
      ConsiderationsAffectedByUncertainFutureEvents.saveAndContinue()
      Then("the DeferringPayment page is shown")
      DeferringPayment.verifyPageTitle(DeferringPayment.pageTitle)

      When("the user confirms the purchaser is applying for a deferment")
      DeferringPayment.radioButton(DeferringPayment.yes)
      DeferringPayment.saveAndContinue()

      Then(
        "user is navigated to the use of land or property page(Only if property type is selected as non - residential or mixed )"
      )
      WhatIsThePropertyUsedFor.verifyPageTitle(WhatIsThePropertyUsedFor.pageTitle)
      And("user selects checkboxes and continues")
      WhatIsThePropertyUsedFor.checkbox(WhatIsThePropertyUsedFor.other, true)
      WhatIsThePropertyUsedFor.checkbox(WhatIsThePropertyUsedFor.otherIndustrialUnit, true)
      WhatIsThePropertyUsedFor.saveAndContinue()

      Then("the SaleOfABusiness page is shown")
      SaleOfABusiness.verifyPageTitle(SaleOfABusiness.pageTitle)
      When("the user confirms the transaction is part of the sale of a business")
      SaleOfABusiness.radioButton(SaleOfABusiness.yes)
      SaleOfABusiness.saveAndContinue()
      Then("user provides what is included in sale of business")
      WhatIncludedInSale.verifyPageTitle(WhatIncludedInSale.pageTitle)
      And("user selects checkboxes and continues")
      WhatIncludedInSale.checkbox(WhatIncludedInSale.others, true)
      WhatIncludedInSale.saveAndContinue()
      Then("user is navigated to the Total Consideration of all assets page")
      TotalConsiderationOfAllAssets.verifyPageTitle(TotalConsiderationOfAllAssets.pageTitle)

      When("the user enters the amount and click continue")
      TotalConsiderationOfAllAssets.input(
        By.id(TotalConsiderationOfAllAssets.totalConsiderationOfAllAssets),
        TotalConsiderationOfAllAssets.totalConsiderationOfAllAssetsInput
      )
      TotalConsiderationOfAllAssets.saveAndContinue()
      Then("the user is navigated to the applied for  CAP1 or NSBC page")
      AppliedForCAP1OrNSBC.verifyPageTitle(AppliedForCAP1OrNSBC.pageTitle)

      When("the user selects yes radio button and continues")
      AppliedForCAP1OrNSBC.radioButton(AppliedForCAP1OrNSBC.yes)
      AppliedForCAP1OrNSBC.saveAndContinue()

      Then("the user is navigated to the have you followed the ruling under CAP1 or NSBC page")
      HaveYouFollowedTheRuling.verifyPageTitle(
        HaveYouFollowedTheRuling.pageTitle
      )

      When("the user selects yes radio button and continues")
      HaveYouFollowedTheRuling.radioButton(HaveYouFollowedTheRuling.no)
      HaveYouFollowedTheRuling.saveAndContinue()
      Then("the user is navigated to Restrictions Covenants or Conditions Page")
      RestrictionsConvenantsOrConditions.verifyPageTitle(RestrictionsConvenantsOrConditions.pageTitle)

      When("the user selects yes radio button and continues")
      RestrictionsConvenantsOrConditions.radioButton(RestrictionsConvenantsOrConditions.no)
      RestrictionsConvenantsOrConditions.saveAndContinue()

      Then("the ExchangeOrPartExchange page is shown")
      ExchangeOrPartExchange.verifyPageTitle(ExchangeOrPartExchange.pageTitle)

      When("the user confirms the land is being exchanged or part exchanged")
      ExchangeOrPartExchange.radioButton(ExchangeOrPartExchange.yes)
      ExchangeOrPartExchange.saveAndContinue()
      Then("user is navigated to address flow and fills the details")
      TransactionExchangeAddress.clickAddressManually()
      TransactionExchangeAddress.verifyPageTitle(TransactionExchangeAddress.editPageTitle)
      TransactionExchangeAddress.enterAddressManually("523", "AGC", "TE11 1TS")
      And("user is navigated to confirm the address page and continues")
      TransactionExchangeAddress.verifyPageTitle(TransactionExchangeAddress.confirmPageTitle)
      TransactionExchangeAddress.clickSubmitButton()
      Then("user navigates to Exercising an option page")
      ExercisingAnOption.verifyPageTitle(ExercisingAnOption.pageTitle)
      And("user selects yes radio button and continues")
      ExercisingAnOption.radioButton(ExercisingAnOption.yes)
      ExercisingAnOption.saveAndContinue()
      Then("the TransactionCheckYourAnswers page is shown")
      TransactionCheckYourAnswers.verifyPageTitle(TransactionCheckYourAnswers.pageTitle)

      When("the user updates the total consideration")
      TransactionCheckYourAnswers.clickTotalConsiderationOfTransaction()
      TotalConsiderationOfTransaction.verifyPageTitle(TotalConsiderationOfTransaction.pageTitle)
      TotalConsiderationOfTransaction.input(
        By.id(TotalConsiderationOfTransaction.totalConsideration),
        TotalConsiderationOfTransaction.totalConsiderationInputCYA
      )
      TotalConsiderationOfTransaction.saveAndContinue()
      Then("the TransactionCheckYourAnswers page is shown")
      TransactionCheckYourAnswers.verifyPageTitle(TransactionCheckYourAnswers.pageTitle)

      When("the user confirms there is not vat included in the total consideration")
      TransactionCheckYourAnswers.clickIsVATIncluded()
      IsVatIncludedInTotalConsideration.verifyPageTitle(IsVatIncludedInTotalConsideration.pageTitle)
      IsVatIncludedInTotalConsideration.radioButton(IsVatIncludedInTotalConsideration.no)
      IsVatIncludedInTotalConsideration.saveAndContinue()
      Then("the TransactionCheckYourAnswers page is shown")
      TransactionCheckYourAnswers.verifyPageTitle(TransactionCheckYourAnswers.pageTitle)

      When("the user updates their answer to provide the forms of consideration")
      TransactionCheckYourAnswers.clickFormsOfConsideration()
      FormsOfConsideration.verifyPageTitle(FormsOfConsideration.pageTitle)
      FormsOfConsideration.checkbox(FormsOfConsideration.cash, true)
      FormsOfConsideration.checkbox(FormsOfConsideration.building_works, true)
      FormsOfConsideration.saveAndContinue()
      Then("the TransactionCheckYourAnswers page is shown")
      TransactionCheckYourAnswers.verifyPageTitle(TransactionCheckYourAnswers.pageTitle)

      When("the user submits the transaction questions")
      TransactionCheckYourAnswers.saveAndContinue()
      Then("the ReturnTaskList page is shown")
      ReturnTaskList.verifyPageTitle(ReturnTaskList.pageTitle)
    }
  }
}
