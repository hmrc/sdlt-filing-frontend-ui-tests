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

      When("the user opens the vendor agent questions")
      WhoIsTheVendor.clickLinkById("task-list-link-vendor-agent-questions")

      Then("the Vendor Agent Before You Start page is displayed")
      VendorAgentBeforeYouStart.verifyPageTitle(VendorAgentBeforeYouStart.pageTitle)

      When("the user starts the vendor agent questions journey")
      VendorAgentBeforeYouStart.radioButton(VendorAgentBeforeYouStart.yes)
      VendorAgentBeforeYouStart.saveAndContinue()

      Then("the Vendor Agent Name page is displayed")
      VendorAgentName.verifyPageTitle(VendorAgentName.pageTitle)

      When("the user inputs the vendor agent name")
      VendorAgentName.input(
        By.id(VendorAgentName.agentName),
        VendorAgentName.agentNameInput
      )
      VendorAgentName.saveAndContinue()

      Then("the Vendor Agent Address page is displayed")
      VendorAgentAddress.verifyPageTitle(VendorAgentAddress.pageTitle)

      When("the user enters the vendor agent address manually")
      VendorAgentAddress.clickAddressManually()
      VendorAgentAddress.verifyPageTitle(VendorAgentAddress.editPageTitleAgent)
      VendorAgentAddress.enterAddressManually("523", "AGC", "TE12 1TS")

      Then("the Confirm Vendor Agent Property Address page is displayed")
      VendorAgentAddress.verifyPageTitle(VendorAgentAddress.confirmPageTitleAgent)

      When("the user confirms the address")
      VendorPropertyAddress.clickContinueButton()

      Then("the Add Agent Contact Details page is displayed")
      AddVendorAgentContactDetails.verifyPageTitle(AddVendorAgentContactDetails.pageTitle)

      When("the user chooses to add contact details")
      AddVendorAgentContactDetails.radioButton(AddVendorAgentContactDetails.yes)
      AddVendorAgentContactDetails.saveAndContinue()

      Then("the Enter Agent Contact Details page is displayed")
      VendorAgentEnterContactDetails.verifyPageTitle(VendorAgentEnterContactDetails.pageTitle)

      When("the user enters a phone number")
      VendorAgentEnterContactDetails.input(
        By.id(VendorAgentEnterContactDetails.phoneNumber),
        VendorAgentEnterContactDetails.phoneNumberInput
      )
      And("and email address")
      VendorAgentEnterContactDetails.input(
        By.id(VendorAgentEnterContactDetails.emailAddress),
        VendorAgentEnterContactDetails.emailAddressInput
      )
      VendorAgentEnterContactDetails.saveAndContinue()

      Then("the Add Agent Reference Number page is displayed")
      AddVendorAgentReferenceNumber.verifyPageTitle(AddVendorAgentReferenceNumber.pageTitle)

      When("the user chooses to add a reference number")
      AddVendorAgentReferenceNumber.radioButton(AddVendorAgentReferenceNumber.yes)
      AddVendorAgentReferenceNumber.saveAndContinue()

      Then("the Enter Agent Reference Number page is displayed")
      VendorAgentEnterReferenceNumber.verifyPageTitle(VendorAgentEnterReferenceNumber.pageTitle)

      When("the user enters an agent reference number")
      VendorAgentEnterReferenceNumber.input(
        By.id(VendorAgentEnterReferenceNumber.agentReference),
        VendorAgentEnterReferenceNumber.agentReferenceNumber
      )
      VendorAgentEnterReferenceNumber.saveAndContinue()

      Then("the Vendor Agent Check Your Answers page is displayed")
      VendorAgentCheckYourAnswers.verifyPageTitle(VendorAgentCheckYourAnswers.pageTitle)

      When("the user submits the vendor agent questions")
      VendorAgentCheckYourAnswers.saveAndContinue()

      Then("the Vendor Agent Overview page is displayed")
      VendorAgentOverview.verifyPageTitle(VendorAgentOverview.pageTitle)

      When("the user adds a vendor agent")
      VendorAgentOverview.radioButton(VendorAgentOverview.yes)
      VendorAgentOverview.saveAndContinue()

      Then("the Vendor Agent Name page is displayed")
      VendorAgentName.verifyPageTitle(VendorAgentName.pageTitle)
    }

    Scenario(
      "Complete the Vendor Agent journey without contact details and reference information",
      VendorAgentJourney
    ) {

      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("no-vendor"))

      When("the user opens the vendor agent questions")
      WhoIsTheVendor.clickLinkById("task-list-link-vendor-agent-questions")

      Then("the Vendor Agent Before You Start page is displayed")
      VendorAgentBeforeYouStart.verifyPageTitle(VendorAgentBeforeYouStart.pageTitle)

      When("the user starts the vendor agent questions journey")
      VendorAgentBeforeYouStart.radioButton(VendorAgentBeforeYouStart.yes)
      VendorAgentBeforeYouStart.saveAndContinue()

      Then("the Vendor Agent Name page is displayed")
      VendorAgentName.verifyPageTitle(VendorAgentName.pageTitle)

      When("the user inputs the vendor agent name")
      VendorAgentName.input(
        By.id(VendorAgentName.agentName),
        VendorAgentName.agentNameInput
      )
      VendorAgentName.saveAndContinue()

      Then("the Vendor Agent Address page is displayed")
      VendorAgentAddress.verifyPageTitle(VendorAgentAddress.pageTitle)

      When("the user enters the vendor address manually")
      VendorAgentAddress.clickAddressManually()
      VendorAgentAddress.verifyPageTitle(VendorAgentAddress.editPageTitleAgent)
      VendorAgentAddress.enterAddressManually("523", "AGC", "TE12 1TS")

      Then("the Confirm Vendor Property Address page is displayed")
      VendorAgentAddress.verifyPageTitle(VendorAgentAddress.confirmPageTitleAgent)

      When("the user confirms the address")
      VendorPropertyAddress.clickContinueButton()

      Then("the Add Agent Contact Details page is displayed")
      AddVendorAgentContactDetails.verifyPageTitle(AddVendorAgentContactDetails.pageTitle)

      When("the user chooses not to add contact details")
      AddVendorAgentContactDetails.radioButton(AddVendorAgentContactDetails.no)
      AddVendorAgentContactDetails.saveAndContinue()

      Then("the Add Agent Reference Number page is displayed")
      AddVendorAgentReferenceNumber.verifyPageTitle(AddVendorAgentReferenceNumber.pageTitle)

      When("the user chooses not to add a reference number")
      AddVendorAgentReferenceNumber.radioButton(AddVendorAgentReferenceNumber.no)
      AddVendorAgentReferenceNumber.saveAndContinue()

      Then("the Vendor Agent Check Your Answers page is displayed")
      VendorAgentCheckYourAnswers.verifyPageTitle(VendorAgentCheckYourAnswers.pageTitle)

      When("the user updates the vendor agent name")
      VendorAgentCheckYourAnswers.clickVendorAgentNameChange()
      VendorAgentName.verifyPageTitle(VendorAgentName.pageTitle)
      VendorAgentName.input(By.id(VendorAgentName.agentName), VendorAgentName.agentNameInput2)
      VendorAgentName.saveAndContinue()

      Then("the Vendor Agent name is updated on the Check Your Answers page")
      VendorAgentCheckYourAnswers.verifyPageTitle(VendorAgentCheckYourAnswers.pageTitle)

      When("the user updates the vendor agent address")
      VendorAgentCheckYourAnswers.clickVendorAgentAddressChange()
      VendorAgentAddress.verifyPageTitle(VendorAgentAddress.pageTitle2)
      VendorAgentAddress.clickAddressManually()
      VendorAgentAddress.verifyPageTitle(VendorAgentAddress.editPageTitleAgent2)
      VendorAgentAddress.enterAddressManually("123", "TEST", "ZZ11 1ZZ")
      VendorAgentAddress.verifyPageTitle(VendorAgentAddress.confirmPageTitleAgent2)
      VendorAgentAddress.clickContinueButton()

      Then("the Vendor Agent address is updated on the Check Your Answers page")
      VendorAgentCheckYourAnswers.verifyPageTitle(VendorAgentCheckYourAnswers.pageTitle)

      When("the user chooses to add contact details")
      VendorAgentCheckYourAnswers.clickAddAgentContactDetailsChange()
      AddVendorAgentContactDetails.verifyPageTitle(AddVendorAgentContactDetails.pageTitle)
      AddVendorAgentContactDetails.radioButton(AddVendorAgentContactDetails.yes)
      AddVendorAgentContactDetails.saveAndContinue()

      Then("the Vendor Agent contact details are updated on the Check Your Answers page")
      VendorAgentCheckYourAnswers.verifyPageTitle(VendorAgentCheckYourAnswers.pageTitle)

      When("the user chooses to enter contact details")
      VendorAgentCheckYourAnswers.clickVendorAgentContactDetailsChange()
      VendorAgentEnterContactDetails.verifyPageTitle(VendorAgentEnterContactDetails.pageTitle)
      VendorAgentEnterContactDetails.input(
        By.id(VendorAgentEnterContactDetails.phoneNumber),
        VendorAgentEnterContactDetails.phoneNumberInput
      )
      VendorAgentEnterContactDetails.saveAndContinue()

      Then("the Vendor Agent contact details are updated on the Check Your Answers page")
      VendorAgentCheckYourAnswers.verifyPageTitle(VendorAgentCheckYourAnswers.pageTitle)

      When("the user chooses to add a reference number")
      VendorAgentCheckYourAnswers.clickDoYouWantToAddReferenceChange()
      AddVendorAgentReferenceNumber.verifyPageTitle(AddVendorAgentReferenceNumber.pageTitle)
      AddVendorAgentReferenceNumber.radioButton(AddVendorAgentReferenceNumber.yes)
      AddVendorAgentReferenceNumber.saveAndContinue()

      Then("the Vendor Agent reference number is updated on the Check Your Answers page")
      VendorAgentCheckYourAnswers.verifyPageTitle(VendorAgentCheckYourAnswers.pageTitle)

      When("the user enters the reference number")
      VendorAgentCheckYourAnswers.clickVendorAgentReferenceNumberChange()
      VendorAgentEnterReferenceNumber.verifyPageTitle(VendorAgentEnterReferenceNumber.pageTitle)
      VendorAgentEnterReferenceNumber.input(
        By.id(VendorAgentEnterReferenceNumber.agentReference),
        VendorAgentEnterReferenceNumber.agentReferenceNumber2
      )
      VendorAgentEnterReferenceNumber.saveAndContinue()

      Then("the Vendor Agent Check Your Answers page is displayed")
      VendorAgentCheckYourAnswers.verifyPageTitle(VendorAgentCheckYourAnswers.pageTitle)

      When("the user submits the vendor agent questions")
      VendorAgentCheckYourAnswers.saveAndContinue()

      Then("the Vendor Agent Overview page is displayed")
      VendorAgentOverview.verifyPageTitle(VendorAgentOverview.pageTitle)
    }

    Scenario(
      "Begin the Vendor Agent journey and remove a vendor agent",
      VendorAgentJourney
    ) {

      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("vendor-agent-and-main-vendor-represented-by-agent"))

      When("the user opens the vendor agent questions")
      WhoIsTheVendor.clickLinkById("task-list-link-vendor-agent-questions")

      Then("the Vendor Agent Overview page is displayed")
      VendorAgentOverview.verifyPageTitle(VendorAgentOverview.pageTitle)

      When("the user removes an existing vendor")
      VendorAgentOverview.clickVendorAgentRemove()

      Then("the Remove Vendor Agent page is displayed")
      RemoveVendorAgent.verifyPageTitle(RemoveVendorAgent.pageTitle)

      When("the user confirms the vendor agent removal")
      RemoveVendorAgent.radioButton(RemoveVendorAgent.yes)
      RemoveVendorAgent.saveAndContinue()

      Then("the Vendor Agent Overview page is displayed")
      VendorAgentOverview.verifyPageTitle(VendorAgentOverview.pageTitle)

      When("the user edits the Vendor Agent details")
      VendorAgentOverview.clickVendorAgentChange()

      Then("the Vendor Agent Check Your Answers page is displayed")
      VendorAgentCheckYourAnswers.verifyPageTitle(VendorAgentCheckYourAnswers.pageTitle)
    }
  }
}
