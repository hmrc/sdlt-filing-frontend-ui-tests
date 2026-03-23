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
import uk.gov.hmrc.ui.pages.Vendor.*
import uk.gov.hmrc.ui.pages.VendorAgent.*
import uk.gov.hmrc.ui.tags.*
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation

class VendorAgentQuestionsSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("SDLT Filing Frontend Vendor Questions") {
    Scenario(
      "Complete the Vendor Agent journey with contact details and reference information",
      VendorAgentJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("no-vendor"))
      When("the user clicks on the 'Vendor Agent Questions' link")
      WhoIsTheVendor.clickLinkById("task-list-link-vendor-agent-questions")
      Then("the user is navigated to the Vendor Agent Before You Start page")
      VendorAgentBeforeYouStart.verifyPageTitle(VendorAgentBeforeYouStart.pageTitle)
      When("the user selects the 'Yes' radio button")
      VendorAgentBeforeYouStart.radioButton(VendorAgentBeforeYouStart.yes)
      And("clicks the Save and continue button")
      VendorAgentBeforeYouStart.saveAndContinue()
      Then("the user is navigated to the Vendor Agent Name page")
      VendorAgentName.verifyPageTitle(VendorAgentName.pageTitle)
      When("the user inputs their Vendor Agent's name")
      VendorAgentName.input(
        By.id(VendorAgentName.agentName),
        VendorAgentName.agentNameInput
      )
      And("clicks the Save and continue button")
      VendorAgentName.saveAndContinue()
      Then("the user is navigated to the Vendor Agent Address page")
      VendorAgentAddress.verifyPageTitle(VendorAgentAddress.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      VendorAgentAddress.clickAddressManually()
      And("enters their address manually")
      VendorAgentAddress.verifyPageTitle(VendorAgentAddress.editPageTitleAgent)
      VendorAgentAddress.enterAddressManually("523", "AGC", "TE12 1TS")
      Then("the user is navigated to the Vendor Agent Address page to 'Review and confirm' the address")
      VendorAgentAddress.verifyPageTitle(VendorAgentAddress.confirmPageTitleAgent)
      When("the user clicks the Confirm address button")
      VendorPropertyAddress.clickContinueButton()
      Then("the user is navigated to the Add Agent Contact Details page")
      AddVendorAgentContactDetails.verifyPageTitle(AddVendorAgentContactDetails.pageTitle)
      When("the user selects the 'Yes' radio button")
      AddVendorAgentContactDetails.radioButton(AddVendorAgentContactDetails.yes)
      And("clicks the Save and continue button")
      AddVendorAgentContactDetails.saveAndContinue()
      Then("the user is navigated to the Enter Agent Contact Details page")
      VendorAgentEnterContactDetails.verifyPageTitle(VendorAgentEnterContactDetails.pageTitle)
      When("the user inputs their phone number")
      VendorAgentEnterContactDetails.input(
        By.id(VendorAgentEnterContactDetails.phoneNumber),
        VendorAgentEnterContactDetails.phoneNumberInput
      )
      And("inputs their email address")
      VendorAgentEnterContactDetails.input(
        By.id(VendorAgentEnterContactDetails.emailAddress),
        VendorAgentEnterContactDetails.emailAddressInput
      )
      And("clicks the Save and continue button")
      VendorAgentEnterContactDetails.saveAndContinue()
      Then("the user is navigated to Add Agent Reference Number page")
      AddVendorAgentReferenceNumber.verifyPageTitle(AddVendorAgentReferenceNumber.pageTitle)
      When("the user selects the 'Yes' radio button")
      AddVendorAgentReferenceNumber.radioButton(AddVendorAgentReferenceNumber.yes)
      And("clicks the Save and continue button")
      AddVendorAgentReferenceNumber.saveAndContinue()
      Then("the user is navigated to the Enter Agent Reference Number page")
      VendorAgentEnterReferenceNumber.verifyPageTitle(VendorAgentEnterReferenceNumber.pageTitle)
      When("the user enters their agent reference number")
      VendorAgentEnterReferenceNumber.input(
        By.id(VendorAgentEnterReferenceNumber.agentReference),
        VendorAgentEnterReferenceNumber.agentReferenceNumber
      )
      And("clicks the Save and continue button")
      VendorAgentEnterReferenceNumber.saveAndContinue()
      Then("the user is navigated to the Vendor Agent Check Your Answers page")
      VendorAgentCheckYourAnswers.verifyPageTitle(VendorAgentCheckYourAnswers.pageTitle)
      When("the user clicks the Save and continue button")
      VendorAgentCheckYourAnswers.saveAndContinue()
      Then("the user is navigated to the Vendor Agent Overview page")
      VendorAgentOverview.verifyPageTitle(VendorAgentOverview.pageTitle)
      When("the user selects the 'Yes' radio button")
      VendorAgentOverview.radioButton(VendorAgentOverview.yes)
      And("clicks the Save and continue button")
      VendorAgentOverview.saveAndContinue()
      Then("the user is navigated to the Vendor Agent Name page")
      VendorAgentName.verifyPageTitle(VendorAgentName.pageTitle)
    }

    Scenario(
      "Complete the Vendor Agent journey with no contact details and reference information",
      VendorAgentJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("no-vendor"))
      When("the user clicks on the 'Vendor Agent Questions' link")
      WhoIsTheVendor.clickLinkById("task-list-link-vendor-agent-questions")
      Then("the user is navigated to the Vendor Agent Before You Start page")
      VendorAgentBeforeYouStart.verifyPageTitle(VendorAgentBeforeYouStart.pageTitle)
      When("the user selects the 'Yes' radio button")
      VendorAgentBeforeYouStart.radioButton(VendorAgentBeforeYouStart.yes)
      And("clicks the Save and continue button")
      VendorAgentBeforeYouStart.saveAndContinue()
      Then("the user is navigated to the Vendor Agent Name page")
      VendorAgentName.verifyPageTitle(VendorAgentName.pageTitle)
      When("the user inputs their Vendor Agent's name")
      VendorAgentName.input(
        By.id(VendorAgentName.agentName),
        VendorAgentName.agentNameInput
      )
      And("clicks the Save and continue button")
      VendorAgentName.saveAndContinue()
      Then("the user is navigated to the Vendor Agent Address page")
      VendorAgentAddress.verifyPageTitle(VendorAgentAddress.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      VendorAgentAddress.clickAddressManually()
      And("enters their address manually")
      VendorAgentAddress.verifyPageTitle(VendorAgentAddress.editPageTitleAgent)
      VendorAgentAddress.enterAddressManually("523", "AGC", "TE12 1TS")
      Then("the user is navigated to the Vendor Agent Address page to 'Review and confirm' the address")
      VendorAgentAddress.verifyPageTitle(VendorAgentAddress.confirmPageTitleAgent)
      When("the user clicks the Confirm address button")
      VendorPropertyAddress.clickContinueButton()
      Then("the user is navigated to the Add Agent Contact Details page")
      AddVendorAgentContactDetails.verifyPageTitle(AddVendorAgentContactDetails.pageTitle)
      When("the user selects the 'No' radio button")
      AddVendorAgentContactDetails.radioButton(AddVendorAgentContactDetails.no)
      And("clicks the Save and continue button")
      AddVendorAgentContactDetails.saveAndContinue()
      Then("the user is navigated to Add Agent Reference Number page")
      AddVendorAgentReferenceNumber.verifyPageTitle(AddVendorAgentReferenceNumber.pageTitle)
      When("the user selects the 'No' radio button")
      AddVendorAgentReferenceNumber.radioButton(AddVendorAgentReferenceNumber.no)
      And("clicks the Save and continue button")
      AddVendorAgentReferenceNumber.saveAndContinue()
      Then("the user is navigated to the Vendor Agent Check Your Answers page")
      VendorAgentCheckYourAnswers.verifyPageTitle(VendorAgentCheckYourAnswers.pageTitle)
      When("the user clicks the 'Change' link for Agent name")
      VendorAgentCheckYourAnswers.clickVendorAgentNameChange()
      Then("the user is navigated to the Vendor Agent Name page")
      VendorAgentName.verifyPageTitle(VendorAgentName.pageTitle)
      When("the user updates their agent's name")
      VendorAgentName.input(By.id(VendorAgentName.agentName), VendorAgentName.agentNameInput2)
      And("clicks the Save and continue button")
      VendorAgentName.saveAndContinue()
      Then("the user is navigated to the Vendor Agent Check Your Answers page")
      VendorAgentCheckYourAnswers.verifyPageTitle(VendorAgentCheckYourAnswers.pageTitle)
      When("the user clicks the 'Change' link for Agent Address")
      VendorAgentCheckYourAnswers.clickVendorAgentAddressChange()
      Then("the user is navigated to the Vendor Agent Address page")
      VendorAgentAddress.verifyPageTitle(VendorAgentAddress.pageTitle2)
      When("the user clicks on the 'Enter the address manually' link")
      VendorAgentAddress.clickAddressManually()
      And("enters their updated address manually")
      VendorAgentAddress.verifyPageTitle(VendorAgentAddress.editPageTitleAgent2)
      VendorAgentAddress.enterAddressManually("123", "TEST", "ZZ11 1ZZ")
      Then("the user is navigated to the Vendor Agent Address page to 'Review and confirm' the address")
      VendorAgentAddress.verifyPageTitle(VendorAgentAddress.confirmPageTitleAgent2)
      When("the user clicks the Confirm address button")
      VendorAgentAddress.clickContinueButton()
      Then("the user is navigated to the Vendor Agent Check Your Answers page")
      VendorAgentCheckYourAnswers.verifyPageTitle(VendorAgentCheckYourAnswers.pageTitle)
      When("the user clicks the 'Change' link for Do you want to add contact details")
      VendorAgentCheckYourAnswers.clickAddAgentContactDetailsChange()
      Then("the user is navigated to the Add Agent Contact Details page")
      AddVendorAgentContactDetails.verifyPageTitle(AddVendorAgentContactDetails.pageTitle)
      When("the user selects the 'Yes' radio button")
      AddVendorAgentContactDetails.radioButton(AddVendorAgentContactDetails.yes)
      And("clicks the Save and continue button")
      AddVendorAgentContactDetails.saveAndContinue()
      Then("the user is navigated to the Vendor Agent Check Your Answers page")
      VendorAgentCheckYourAnswers.verifyPageTitle(VendorAgentCheckYourAnswers.pageTitle)
      When("the user clicks the 'Change' link for Enter Agent Contact details")
      VendorAgentCheckYourAnswers.clickVendorAgentContactDetailsChange()
      Then("the user is navigated to the Enter Agent Contact Details page")
      VendorAgentEnterContactDetails.verifyPageTitle(VendorAgentEnterContactDetails.pageTitle)
      When("the user updates their phone number")
      VendorAgentEnterContactDetails.input(
        By.id(VendorAgentEnterContactDetails.phoneNumber),
        VendorAgentEnterContactDetails.phoneNumberInput
      )
      And("the user clicks the Save and continue button")
      VendorAgentEnterContactDetails.saveAndContinue()
      Then("the user is navigated to the Vendor Agent Check Your Answers page")
      VendorAgentCheckYourAnswers.verifyPageTitle(VendorAgentCheckYourAnswers.pageTitle)
      When("the user clicks the 'Change' link for Do you want to add a reference")
      VendorAgentCheckYourAnswers.clickDoYouWantToAddReferenceChange()
      Then("the user is navigated to Add Agent Reference Number page")
      AddVendorAgentReferenceNumber.verifyPageTitle(AddVendorAgentReferenceNumber.pageTitle)
      When("the user selects the 'Yes' radio button")
      AddVendorAgentReferenceNumber.radioButton(AddVendorAgentReferenceNumber.yes)
      And("clicks the Save and continue button")
      AddVendorAgentReferenceNumber.saveAndContinue()
      Then("the user is navigated to the Vendor Agent Check Your Answers page")
      VendorAgentCheckYourAnswers.verifyPageTitle(VendorAgentCheckYourAnswers.pageTitle)
      When("the user clicks the 'Change' link for Enter Agent Reference Number")
      VendorAgentCheckYourAnswers.clickVendorAgentReferenceNumberChange()
      Then("the user is navigated to the Enter Agent Reference Number page")
      VendorAgentEnterReferenceNumber.verifyPageTitle(VendorAgentEnterReferenceNumber.pageTitle)
      When("the user enters their agent reference number")
      VendorAgentEnterReferenceNumber.input(
        By.id(VendorAgentEnterReferenceNumber.agentReference),
        VendorAgentEnterReferenceNumber.agentReferenceNumber2
      )
      And("clicks the Save and continue button")
      VendorAgentEnterReferenceNumber.saveAndContinue()
      Then("the user is navigated to the Vendor Agent Check Your Answers page")
      VendorAgentCheckYourAnswers.verifyPageTitle(VendorAgentCheckYourAnswers.pageTitle)
      When("the user clicks the Save and continue button")
      VendorAgentCheckYourAnswers.saveAndContinue()
      Then("User should be navigated to Vendor Agent's overview page")
      VendorAgentOverview.verifyPageTitle(VendorAgentOverview.pageTitle)
    }

    Scenario(
      "Begin the Vendor Agent journey and remove a vendor agent",
      VendorAgentJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("vendor-agent-and-main-vendor-represented-by-agent"))
      When("the user clicks on the 'Vendor Agent Questions' link")
      WhoIsTheVendor.clickLinkById("task-list-link-vendor-agent-questions")
      Then("the user is navigated to the Vendor Agent Overview page")
      VendorAgentOverview.verifyPageTitle(VendorAgentOverview.pageTitle)
      When("the user clicks the 'Remove' link to remove a vendor agent")
      VendorAgentOverview.clickVendorAgentRemove()
      Then("the user is navigated to Are you sure you want to remove the vendor agent page")
      RemoveVendorAgent.verifyPageTitle(RemoveVendorAgent.pageTitle)
      When("the user selects the 'Yes' radio button")
      RemoveVendorAgent.radioButton(RemoveVendorAgent.yes)
      And("clicks the Save and continue button")
      RemoveVendorAgent.saveAndContinue()
      Then("the user is navigated to the Vendor Agent Overview page")
      VendorAgentOverview.verifyPageTitle(VendorAgentOverview.pageTitle)
      When("the user clicks the 'Change' link to amend a vendor agent's details")
      VendorAgentOverview.clickVendorAgentChange()
      Then("the user is navigated to the Vendor Agent Check Your Answers page")
      VendorAgentCheckYourAnswers.verifyPageTitle(VendorAgentCheckYourAnswers.pageTitle)
    }
  }
}
