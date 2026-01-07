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
import uk.gov.hmrc.ui.pages.PurchaserAgent.{PurchaserAgentBeforeYouStartPage, PurchaserAgentContactDetails}
import uk.gov.hmrc.ui.pages.purchaser.{DoesPurchaserHavePhoneNumber, PurchaserContactPhoneNumber}
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
      AuthWizard.login(HASDIRECT, Organisation)
      Then("the user is navigated to the Return Task List page")
      ReturnTaskListPage.navigateToPage(ReturnTaskListPage.pageUrl)
      When("the user clicks on the 'Purchaser Agent Questions' link")
      PurchaserAgentBeforeYouStartPage.clickLinkById("task-list-link-purchaser-agent-questions")
      Then("the user should be navigated to the Before You Start page")
      PurchaserAgentBeforeYouStartPage.verifyPageTitle(PurchaserAgentBeforeYouStartPage.pageTitle)
      And("user selects 'Yes' radio button")
      PurchaserAgentBeforeYouStartPage.radioButton(PurchaserAgentBeforeYouStartPage.yes)
      And("clicks the continue button")
      PurchaserAgentBeforeYouStartPage.saveAndContinue()
      // User navigated to Select Purchaser Agent Details page
      // User selects Add an Agent for return radio button
      // User clicks save and continue
      // User navigated to What is the Agent's Name page
      // User inputs Agent's name
      // User clicks save and continue
      // User navigated to What is the Agent's Address page
      // User inputs Agent's address details
      // User clicks save and continue
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
      // User navigated to Do you want to add reference for this return page
    }

    Scenario("Complete the Purchaser Agent Journey", PurchaserAgentJourney) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation)
      Then("the user is navigated to the Return Task List page")
      ReturnTaskListPage.navigateToPage(ReturnTaskListPage.pageUrl)
      When("the user clicks on the 'Purchaser Agent Questions' link")
      PurchaserAgentBeforeYouStartPage.clickLinkById("task-list-link-purchaser-agent-questions")
      Then("the user should be navigated to the Before You Start page")
      PurchaserAgentBeforeYouStartPage.verifyPageTitle(PurchaserAgentBeforeYouStartPage.pageTitle)
      And("user selects 'Yes' radio button")
      PurchaserAgentBeforeYouStartPage.radioButton(PurchaserAgentBeforeYouStartPage.yes)
      And("clicks the continue button")
      PurchaserAgentBeforeYouStartPage.saveAndContinue()
      // User navigated to Select Purchaser Agent Details page
      // User selects existing agent from list radio button
      // User clicks save and continue
      // User navigated to Do you want to add reference for this return page
    }
  }
}
