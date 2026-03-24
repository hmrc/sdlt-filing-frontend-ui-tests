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
import uk.gov.hmrc.ui.pages.PurchaserAgent.*
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation
import uk.gov.hmrc.ui.tags.*

class PurchaserAgentQuestionsSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("SDLT Filing Frontend Purchaser Agent Journey") {
    Scenario(
      "Complete the Purchaser Agent journey with no purchaser agents",
      PurchaserAgentJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("purchaser-no-agents"))
      When("the user clicks on the 'Purchaser Agent Questions' link")
      Retur.clickLinkById("task-list-link-purchaser-agent-questions")
      Then("the user is navigated to the Before You Start page")
      PurchaserAgentBeforeYouStart.verifyPageTitle(PurchaserAgentBeforeYouStart.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserAgentBeforeYouStart.radioButton(PurchaserAgentBeforeYouStart.yes)
      And("clicks the Continue button")
      PurchaserAgentBeforeYouStart.saveAndContinue()
      Then("the user is navigated to the Select Purchaser Agent page")
      SelectPurchaserAgent.verifyPageTitle(SelectPurchaserAgent.pageTitle)
      When("the user selects 'Add a new agent for this return' radio button")
      SelectPurchaserAgent.radioButton(SelectPurchaserAgent.addNewAgent)
      And("clicks the Save and continue button")
      SelectPurchaserAgent.saveAndContinue()
      Then("the user is navigated to the Agent Name page")
      PurchaserAgentName.verifyPageTitle(PurchaserAgentName.pageTitle)
      When("the user enters their Purchaser Agents name")
      PurchaserAgentName.input(By.id(PurchaserAgentName.agentName), PurchaserAgentName.agentNameInput)
      And("clicks the Save and continue button")
      PurchaserAgentName.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent Address page")
      PurchaserAgentAddress.verifyPageTitle(PurchaserAgentAddress.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      PurchaserAgentAddress.clickAddressManually()
      And("enters their address manually")
      PurchaserAgentAddress.verifyPageTitle(PurchaserAgentAddress.editPageTitleAgent)
      PurchaserAgentAddress.enterAddressManually("523", "AGC", "TE11 1TS")
      Then("the user is navigated to the Purchaser Agent Address page to 'Review and confirm' the address")
      PurchaserAgentAddress.verifyPageTitle(PurchaserAgentAddress.confirmPageTitleAgent)
      And("clicks the Confirm address button")
      PurchaserAgentAddress.clickContinueButton()
      Then("the user is navigated to the Add Purchaser Agent Contact Details page")
      AddPurchaserAgentContactDetails.verifyPageTitle(AddPurchaserAgentContactDetails.pageTitle)
      When("the user selects the 'Yes' radio button")
      AddPurchaserAgentContactDetails.radioButton(AddPurchaserAgentContactDetails.yes)
      And("clicks the Save and continue button")
      AddPurchaserAgentContactDetails.saveAndContinue()
      Then("the user is navigated to the Enter Agent Contact Details Page")
      PurchaserAgentEnterContactDetails.verifyPageTitle(PurchaserAgentEnterContactDetails.pageTitle)
      When("the user enters their Purchaser Agent's phone number")
      PurchaserAgentEnterContactDetails.input(
        By.id(PurchaserAgentEnterContactDetails.emailAddress),
        PurchaserAgentEnterContactDetails.emailAddressInput
      )
      And("enters their Purchaser Agent's email address")
      PurchaserAgentEnterContactDetails.input(
        By.id(PurchaserAgentEnterContactDetails.phoneNumber),
        PurchaserAgentEnterContactDetails.phoneNumberInput
      )
      And("clicks the Save and continue button")
      PurchaserAgentEnterContactDetails.saveAndContinue()
      Then("the user is navigated to the Add Agent Reference Number page")
      AddPurchaserAgentReferenceNumber.verifyPageTitle(AddPurchaserAgentReferenceNumber.pageTitle)
      When("the user selects the 'No' radio button")
      AddPurchaserAgentReferenceNumber.radioButton(AddPurchaserAgentReferenceNumber.no)
      And("clicks the Save and continue button")
      AddPurchaserAgentReferenceNumber.saveAndContinue()
      Then("the user is navigated to the Is Purchaser Agent authorised For Correspondence Page")
      PurchaserAgentAuthorisedForCorrespondence.verifyPageTitle(
        PurchaserAgentAuthorisedForCorrespondence.pageTitle
      )
      When("the user selects the 'Yes' radio button")
      PurchaserAgentAuthorisedForCorrespondence.radioButton(PurchaserAgentAuthorisedForCorrespondence.yes)
      And("clicks the Save and continue button")
      PurchaserAgentAuthorisedForCorrespondence.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent's Check Your Answers page")
      PurchaserAgentCheckYourAnswers.verifyPageTitle(PurchaserAgentCheckYourAnswers.pageTitle)
      When("the user clicks the 'Change' link for Purchaser Agent's name")
      PurchaserAgentCheckYourAnswers.clickPurchaserAgentNameChange()
      Then("the user is navigated to the Purchaser Agent name page")
      PurchaserAgentName.verifyPageTitle(PurchaserAgentName.pageTitle)
      When("the user updates their agent name details")
      PurchaserAgentName.input(By.id(PurchaserAgentName.agentName), PurchaserAgentName.agentNameInput2)
      And("clicks the Save and continue button")
      PurchaserAgentName.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent's Check Your Answers page")
      PurchaserAgentCheckYourAnswers.verifyPageTitle(PurchaserAgentCheckYourAnswers.pageTitle)
      When("the user clicks the 'Change' link for Purchaser Agent's address")
      PurchaserAgentCheckYourAnswers.clickPurchaserAgentAddressChange()
      Then("the user is navigated to the Purchaser Agent's address page")
      PurchaserAgentAddress.verifyPageTitle(PurchaserAgentAddress.pageTitle2)
      When("the user clicks on the 'Enter the address manually' link")
      PurchaserAgentAddress.clickAddressManually()
      And("enters their updated address manually")
      PurchaserAgentAddress.verifyPageTitle(PurchaserAgentAddress.editPageTitleAgent2)
      PurchaserAgentAddress.enterAddressManually("123", "TEST", "ZZ11 1ZZ")
      Then("the user is navigated to the Purchaser Agent Address page to 'Review and confirm' the address")
      PurchaserAgentAddress.verifyPageTitle(PurchaserAgentAddress.confirmPageTitleAgent2)
      And("clicks the Save and continue button")
      PurchaserAgentAddress.clickContinueButton()
      Then("the user is navigated to the Purchaser Agent's Check Your Answers page")
      PurchaserAgentCheckYourAnswers.verifyPageTitle(PurchaserAgentCheckYourAnswers.pageTitle)
      When("the user clicks the 'Change' link for Do you want to add agent contact details")
      PurchaserAgentCheckYourAnswers.clickAddAgentContactDetailsChange()
      Then("the user is navigated to the Add Purchaser Agent Contact Details page")
      AddPurchaserAgentContactDetails.verifyPageTitle(AddPurchaserAgentContactDetails.pageTitle)
      When("the user selects the 'No' radio button")
      AddPurchaserAgentContactDetails.radioButton(AddPurchaserAgentContactDetails.no)
      And("clicks the Save and continue button")
      AddPurchaserAgentContactDetails.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent's Check Your Answers page")
      PurchaserAgentCheckYourAnswers.verifyPageTitle(PurchaserAgentCheckYourAnswers.pageTitle)
      When("the user clicks the 'Change' link for Do you want to add a Reference Number")
      PurchaserAgentCheckYourAnswers.clickDoYouWantToAddReferenceChange()
      Then("the user is navigated to the Add Agent Reference Number page")
      AddPurchaserAgentReferenceNumber.verifyPageTitle(AddPurchaserAgentReferenceNumber.pageTitle)
      When("the user selects the 'Yes' radio button")
      AddPurchaserAgentReferenceNumber.radioButton(AddPurchaserAgentReferenceNumber.yes)
      And("clicks the Save and continue button")
      AddPurchaserAgentReferenceNumber.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent's Check Your Answers page")
      PurchaserAgentCheckYourAnswers.verifyPageTitle(PurchaserAgentCheckYourAnswers.pageTitle)
      When("the user clicks the 'Change' link for Enter Agent Reference Number")
      PurchaserAgentCheckYourAnswers.clickPurchaserAgentReferenceNumberChange()
      Then("the user is navigated to Enter Agent Reference Number page")
      PurchaserAgentEnterReferenceNumber.verifyPageTitle(PurchaserAgentEnterReferenceNumber.pageTitle)
      When("the user enters their Agent Reference Number")
      PurchaserAgentEnterReferenceNumber.input(
        By.id(PurchaserAgentEnterReferenceNumber.purchaserReference),
        PurchaserAgentEnterReferenceNumber.purchaserReferenceInput
      )
      And("clicks the Save and continue button")
      PurchaserAgentEnterReferenceNumber.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent's Check Your Answers page")
      PurchaserAgentCheckYourAnswers.verifyPageTitle(PurchaserAgentCheckYourAnswers.pageTitle)
      When("the user clicks the 'Change' link for Is Agent authorised for Correspondence")
      PurchaserAgentCheckYourAnswers.clickIsAgentAuthorizedForCorrespondenceChange()
      Then("the user is navigated to the Is Purchaser Agent authorised For Correspondence Page")
      PurchaserAgentAuthorisedForCorrespondence.verifyPageTitle(
        PurchaserAgentAuthorisedForCorrespondence.pageTitle
      )
      When("the user selects the 'No' radio button")
      PurchaserAgentAuthorisedForCorrespondence.radioButton(PurchaserAgentAuthorisedForCorrespondence.no)
      And("clicks the Save and continue button")
      PurchaserAgentAuthorisedForCorrespondence.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent's Check Your Answers page")
      PurchaserAgentCheckYourAnswers.verifyPageTitle(PurchaserAgentCheckYourAnswers.pageTitle)
      And("clicks the save and continue button")
      PurchaserAgentCheckYourAnswers.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent Overview page")
      PurchaserAgentOverview.verifyPageTitle(PurchaserAgentOverview.pageTitle)
      When("the user selects 'Yes' radio button")
      PurchaserAgentOverview.radioButton(PurchaserAgentOverview.yes)
      And("clicks the save and continue button")
      PurchaserAgentOverview.saveAndContinue()
    }

    Scenario(
      "Complete the Purchaser Agent Journey with no contact details",
      PurchaserAgentJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("purchaser-no-agents"))
      When("the user clicks on the 'Purchaser Agent Questions' link")
      PurchaserAgentBeforeYouStart.clickLinkById("task-list-link-purchaser-agent-questions")
      Then("the user is navigated to the Before You Start page")
      PurchaserAgentBeforeYouStart.verifyPageTitle(PurchaserAgentBeforeYouStart.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserAgentBeforeYouStart.radioButton(PurchaserAgentBeforeYouStart.yes)
      And("clicks the Continue button")
      PurchaserAgentBeforeYouStart.saveAndContinue()
      Then("the user is navigated to the Select Purchaser Agent page")
      SelectPurchaserAgent.verifyPageTitle(SelectPurchaserAgent.pageTitle)
      When("the user selects 'Add a new agent for this return' radio button")
      SelectPurchaserAgent.radioButton(SelectPurchaserAgent.addNewAgent)
      And("clicks the Save and continue button")
      SelectPurchaserAgent.saveAndContinue()
      Then("the user is navigated to the Agent Name page")
      PurchaserAgentName.verifyPageTitle(PurchaserAgentName.pageTitle)
      When("the user enters their Purchaser Agents name")
      PurchaserAgentName.input(By.id(PurchaserAgentName.agentName), PurchaserAgentName.agentNameInput)
      And("clicks the Save and continue button")
      PurchaserAgentName.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent Address page")
      PurchaserAgentAddress.verifyPageTitle(PurchaserAgentAddress.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      PurchaserAgentAddress.clickAddressManually()
      And("enters their address manually")
      PurchaserAgentAddress.verifyPageTitle(PurchaserAgentAddress.editPageTitleAgent)
      PurchaserAgentAddress.enterAddressManually("523", "AGC", "TE11 1TS")
      Then("the user is navigated to the Purchaser Agent Address page to 'Review and confirm' the address")
      PurchaserAgentAddress.verifyPageTitle(PurchaserAgentAddress.confirmPageTitleAgent)
      And("clicks the Confirm address button")
      PurchaserAgentAddress.clickContinueButton()
      Then("the user is navigated to the Add Purchaser Agent Contact Details page")
      AddPurchaserAgentContactDetails.verifyPageTitle(AddPurchaserAgentContactDetails.pageTitle)
      When("the user selects the 'No' radio button")
      AddPurchaserAgentContactDetails.radioButton(AddPurchaserAgentContactDetails.no)
      And("clicks the Save and continue button")
      AddPurchaserAgentContactDetails.saveAndContinue()
      Then("the user is navigated to the Add Agent Reference Number page")
      AddPurchaserAgentReferenceNumber.verifyPageTitle(AddPurchaserAgentReferenceNumber.pageTitle)
      When("the user selects the 'Yes' radio button")
      AddPurchaserAgentReferenceNumber.radioButton(AddPurchaserAgentReferenceNumber.yes)
      And("clicks the Save and continue button")
      AddPurchaserAgentReferenceNumber.saveAndContinue()
      Then("the user is navigated to Enter Agent Reference Number page")
      PurchaserAgentEnterReferenceNumber.verifyPageTitle(PurchaserAgentEnterReferenceNumber.pageTitle)
      When("the user enters their Agent Reference Number")
      PurchaserAgentEnterReferenceNumber.input(
        By.id(PurchaserAgentEnterReferenceNumber.purchaserReference),
        PurchaserAgentEnterReferenceNumber.purchaserReferenceInput
      )
      And("clicks the Save and continue button")
      PurchaserAgentEnterReferenceNumber.saveAndContinue()
      Then("the user is navigated to the Is Purchaser Agent authorised For Correspondence Page")
      PurchaserAgentAuthorisedForCorrespondence.verifyPageTitle(
        PurchaserAgentAuthorisedForCorrespondence.pageTitle
      )
      When("the user selects the 'Yes' radio button")
      PurchaserAgentAuthorisedForCorrespondence.radioButton(PurchaserAgentAuthorisedForCorrespondence.no)
      And("clicks the Save and continue button")
      PurchaserAgentAuthorisedForCorrespondence.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent's Check Your Answers page")
      PurchaserAgentCheckYourAnswers.verifyPageTitle(PurchaserAgentCheckYourAnswers.pageTitle)
      When("the user clicks the 'Change' link for Do you want to add agent contact details")
      PurchaserAgentCheckYourAnswers.clickAddAgentContactDetailsChange()
      Then("the user is navigated to the Add Purchaser Agent Contact Details page")
      AddPurchaserAgentContactDetails.verifyPageTitle(AddPurchaserAgentContactDetails.pageTitle)
      When("the user selects the 'Yes' radio button")
      AddPurchaserAgentContactDetails.radioButton(AddPurchaserAgentContactDetails.yes)
      And("clicks the Save and continue button")
      AddPurchaserAgentContactDetails.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent's Check Your Answers page")
      PurchaserAgentCheckYourAnswers.verifyPageTitle(PurchaserAgentCheckYourAnswers.pageTitle)
      When("the user clicks the 'Change' link for Enter Purchaser Agent Contact Details")
      PurchaserAgentCheckYourAnswers.clickPurchaserAgentContactDetailsChange()
      Then("the user is navigated to the Enter Agent Contact Details Page")
      PurchaserAgentEnterContactDetails.verifyPageTitle(PurchaserAgentEnterContactDetails.pageTitle)
      When("the user enters their Purchaser Agent's phone number")
      PurchaserAgentEnterContactDetails.input(
        By.id(PurchaserAgentEnterContactDetails.emailAddress),
        PurchaserAgentEnterContactDetails.emailAddressInput
      )
      And("enters their Purchaser Agent's email address")
      PurchaserAgentEnterContactDetails.input(
        By.id(PurchaserAgentEnterContactDetails.phoneNumber),
        PurchaserAgentEnterContactDetails.phoneNumberInput
      )
      And("clicks the Save and continue button")
      PurchaserAgentEnterContactDetails.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent's Check Your Answers page")
      PurchaserAgentCheckYourAnswers.verifyPageTitle(PurchaserAgentCheckYourAnswers.pageTitle)
      And("clicks the save and continue button")
      PurchaserAgentCheckYourAnswers.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent Overview page")
      PurchaserAgentOverview.verifyPageTitle(PurchaserAgentOverview.pageTitle)
      When("the user selects 'Yes' radio button")
      PurchaserAgentOverview.radioButton(PurchaserAgentOverview.yes)
      And("clicks the save and continue button")
      PurchaserAgentOverview.saveAndContinue()
    }

    Scenario(
      "Complete the Purchaser Agent Journey for an existing agent",
      PurchaserAgentJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("purchaser-no-agents"))
      When("the user clicks on the 'Purchaser Agent Questions' link")
      PurchaserAgentBeforeYouStart.clickLinkById("task-list-link-purchaser-agent-questions")
      Then("the user is navigated to the Before You Start page")
      PurchaserAgentBeforeYouStart.verifyPageTitle(PurchaserAgentBeforeYouStart.pageTitle)
      When("the user selects the 'Yes' radio button")
      PurchaserAgentBeforeYouStart.radioButton(PurchaserAgentBeforeYouStart.yes)
      And("clicks the Continue button")
      PurchaserAgentBeforeYouStart.saveAndContinue()
      Then("the user is navigated to the Select Purchaser Agent page")
      SelectPurchaserAgent.verifyPageTitle(SelectPurchaserAgent.pageTitle)
      When("the user selects 'Smith & Partners LLP, London' radio button")
      SelectPurchaserAgent.radioButton(SelectPurchaserAgent.selectAgent)
      And("clicks the Save and continue button")
      SelectPurchaserAgent.saveAndContinue()
      Then("the user is navigated to the Add Agent Reference Number page")
      AddPurchaserAgentReferenceNumber.verifyPageTitle(AddPurchaserAgentReferenceNumber.pageTitle)
      When("the user selects the 'Yes' radio button")
      AddPurchaserAgentReferenceNumber.radioButton(AddPurchaserAgentReferenceNumber.yes)
      And("clicks the Save and continue button")
      AddPurchaserAgentReferenceNumber.saveAndContinue()
      Then("the user is navigated to Enter Agent Reference Number page")
      PurchaserAgentEnterReferenceNumber.verifyPageTitle(PurchaserAgentEnterReferenceNumber.pageTitle)
      When("the user enters their Agent Reference Number")
      PurchaserAgentEnterReferenceNumber.input(
        By.id(PurchaserAgentEnterReferenceNumber.purchaserReference),
        PurchaserAgentEnterReferenceNumber.purchaserReferenceInput
      )
      And("clicks the Save and continue button")
      PurchaserAgentEnterReferenceNumber.saveAndContinue()
      Then("the user is navigated to the Is Purchaser Agent authorised For Correspondence Page")
      PurchaserAgentAuthorisedForCorrespondence.verifyPageTitle(
        PurchaserAgentAuthorisedForCorrespondence.pageTitle
      )
      When("the user selects the 'Yes' radio button")
      PurchaserAgentAuthorisedForCorrespondence.radioButton(PurchaserAgentAuthorisedForCorrespondence.yes)
      And("clicks the Save and continue button")
      PurchaserAgentAuthorisedForCorrespondence.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent's Check Your Answers page")
      PurchaserAgentCheckYourAnswers.verifyPageTitle(PurchaserAgentCheckYourAnswers.pageTitle)
      And("clicks the Save and continue button")
      PurchaserAgentCheckYourAnswers.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent Overview page")
      PurchaserAgentOverview.verifyPageTitle(PurchaserAgentOverview.pageTitle)
    }

    Scenario(
      "Complete the Purchaser Agent Journey with a completed purchaser agent",
      PurchaserAgentJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("full-purchaser-with-agent"))
      When("the user clicks on the 'Purchaser Agent Questions' link")
      PurchaserAgentBeforeYouStart.clickLinkById("task-list-link-purchaser-agent-questions")
      Then("the user is navigated to the Purchaser Agent Overview page")
      PurchaserAgentOverview.verifyPageTitle(PurchaserAgentOverview.pageTitle)
      When("the user clicks the 'Remove' link to remove a vendor")
      PurchaserAgentOverview.clickPurchaserAgentRemove()
      Then("the user is navigated to the Remove Vendor page")
      RemovePurchaserAgent.verifyPageTitle(RemovePurchaserAgent.pageTitle)
      When("the user selects the 'Yes' radio button")
      RemovePurchaserAgent.radioButton(RemovePurchaserAgent.yes)
      And("clicks the Save and continue button")
      RemovePurchaserAgent.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent Overview page")
      PurchaserAgentOverview.verifyPageTitle(PurchaserAgentOverview.pageTitle)
      When("the user clicks the 'Change' link")
      PurchaserAgentOverview.clickPurchaserAgentChange()
      Then("the user is navigated to the Purchaser Agent's Check Your Answers page")
      PurchaserAgentCheckYourAnswers.verifyPageTitle(PurchaserAgentCheckYourAnswers.pageTitle)
    }
  }
}
