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

class PurchaserAgentSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("SDLT Filing Frontend Purchaser Agent Journey") {
    Scenario("Complete the Purchaser Agent Journey for new return", PurchaserAgentJourney) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("purchaser-agent"))
      When("the user clicks on the 'Purchaser Agent Questions' link")
      PurchaserAgentBeforeYouStartPage.clickLinkById("task-list-link-purchaser-agent-questions")
      Then("the user should be navigated to the Before You Start page")
      PurchaserAgentBeforeYouStartPage.verifyPageTitle(PurchaserAgentBeforeYouStartPage.pageTitle)
      And("user selects 'Yes' radio button")
      PurchaserAgentBeforeYouStartPage.radioButton(PurchaserAgentBeforeYouStartPage.yes)
      And("clicks the continue button")
      PurchaserAgentBeforeYouStartPage.saveAndContinue()
      Then("User navigated to Select Purchaser Agent Details page")
      PurchaserAgentDetailsPage.verifyPageTitle(PurchaserAgentDetailsPage.pageTitle)
      When("User selects 'Add a new agent for this return' radio button")
      PurchaserAgentDetailsPage.radioButton(PurchaserAgentDetailsPage.addNewAgent)
      And("User clicks save and continue")
      PurchaserAgentDetailsPage.saveAndContinue()
      Then("The user should be navigated to the Agents Name Page")
      PurchaserAgentName.verifyPageTitle(PurchaserAgentName.pageTitle)
      And("The user enters their purchaser Agents name")
      PurchaserAgentName.input(By.id(PurchaserAgentName.purchaserAName), PurchaserAgentName.purchaserANameInput)
      And("The User selects save and continue")
      PurchaserAgentName.saveAndContinue()
      Then("the user is navigated to the Purchaser Agent Address page")
      PurchaserAgentAddressPage.verifyPageTitle(PurchaserAgentAddressPage.pageTitle)
      When("the user clicks on the 'Enter the address manually' link")
      PurchaserAgentAddressPage.clickAddressManually()
      And("enters their address manually")
      PurchaserAgentAddressPage.verifyPageTitle(PurchaserAgentAddressPage.editPageTitleAgent)
      PurchaserAgentAddressPage.enterAddressManually("523", "AGC", "TE11 1TS")
      Then("the user is navigated to the Purchaser Agent Address page to 'Review and confirm' the address")
      PurchaserAgentAddressPage.verifyPageTitle(PurchaserAgentAddressPage.confirmPageTitleAgent)
      And("clicks the Confirm address button")
      PurchaserAgentAddressPage.clickContinueButton()

      // User navigated to Do you want to add contact details for the Agent page
      // User selects Yes radio button
      // User clicks save and continue
      // User navigated to What is the Agent's Contact Details page

      // next line to be deleted when navigation completed
      PurchaserAgentContactDetails.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/about-the-purchasers-agent/enter-agent-contact-details"
      )
      Then("User should be navigated to Agent Contact Details Page")
      PurchaserAgentContactDetails.verifyPageTitle(PurchaserAgentContactDetails.pageTitle)
      When("User enters Purchaser Agent's phone number")
      PurchaserAgentContactDetails.input(
        By.id(PurchaserAgentContactDetails.emailAddress),
        PurchaserAgentContactDetails.emailAddressInput
      )
      And("User enters Purchaser Agent's email address")
      PurchaserAgentContactDetails.input(
        By.id(PurchaserAgentContactDetails.phoneNumber),
        PurchaserAgentContactDetails.phoneNumberInput
      )
      And("User clicks save and continue")
      PurchaserAgentContactDetails.saveAndContinue()
      Then("User navigated to Do you want to add reference for this return page")
      PurchaserAgentAddReferencePage.verifyPageTitle(PurchaserAgentAddReferencePage.pageTitle)
      And("User selects Yes radio button")
      PurchaserAgentAddReferencePage.radioButton(PurchaserAgentAddReferencePage.yes)
      And("User clicks save and continue")
      PurchaserAgentAddReferencePage.saveAndContinue()
    }

    Scenario("Complete the Purchaser Agent Journey for additional purchaser", PurchaserAgentJourney, wip) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("no-purchaser"))
      When("the user clicks on the 'Purchaser Agent Questions' link")
      PurchaserAgentBeforeYouStartPage.clickLinkById("task-list-link-purchaser-agent-questions")
      Then("the user should be navigated to the Before You Start page")
      PurchaserAgentBeforeYouStartPage.verifyPageTitle(PurchaserAgentBeforeYouStartPage.pageTitle)
      And("user selects 'Yes' radio button")
      PurchaserAgentBeforeYouStartPage.radioButton(PurchaserAgentBeforeYouStartPage.yes)
      And("clicks the continue button")
      PurchaserAgentBeforeYouStartPage.saveAndContinue()
      Then("User navigated to Select Purchaser Agent Details page")
      PurchaserAgentDetailsPage.verifyPageTitle(PurchaserAgentDetailsPage.pageTitle)
      When("User selects 'Add a new agent for this return' radio button")
      PurchaserAgentDetailsPage.radioButton(PurchaserAgentDetailsPage.selectAgent)
      And("User clicks save and continue")
      PurchaserAgentDetailsPage.saveAndContinue()
      Then("User navigated to Do you want to add reference for this return page")
      PurchaserAgentAddReferencePage.verifyPageTitle(PurchaserAgentAddReferencePage.pageTitle)
      And("User selects Yes radio button")
      PurchaserAgentAddReferencePage.radioButton(PurchaserAgentAddReferencePage.yes)
      And("User clicks save and continue")
      PurchaserAgentAddReferencePage.saveAndContinue()
    }
  }
}
