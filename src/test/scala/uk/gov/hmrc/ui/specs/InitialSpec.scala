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
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, GivenWhenThen}
import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.verbs.ShouldVerb
import uk.gov.hmrc.selenium.webdriver.{Browser, ScreenshotOnFailure}
import uk.gov.hmrc.ui.pages.{AuthWizard, IndividualOrCompanyPage, InitialPage, IsAnIndividualPage, PropertyAddress}
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation

class InitialSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("SDLT Filing frontend Task List Homepage") {
    Scenario("Hit the TaskList with no return id") {
      Given("I enter login using the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation)

      Then("I should be on the initial page")
      InitialPage.verifyPageTitle(InitialPage.pageTitle)
      InitialPage.verifyTaskRowStatus("status-prelimQuestionDetailRow", "Not Started")
      Then("User clicks Prelim Questions Task")
      InitialPage.clickLinkById("task-list-link-prelim-questions")

      Given("User is on the Individual or Company Name page")
      IndividualOrCompanyPage.verifyPageTitle(IndividualOrCompanyPage.pageTitle)
      Then("User input their name or company name and submits")
      IndividualOrCompanyPage.input(By.id("purchaserSurnameOrCompanyName"), "Test Name")
      IndividualOrCompanyPage.clickSubmitButton()

      Given("User is on the Address Look-up screen")
      PropertyAddress.verifyPageTitle(PropertyAddress.pageTitle)
      When("User enter the postcode and conitnue")
      PropertyAddress.enterPostCode("AB123M")
      Then("User navigates to the Addresses screen and select address")
      PropertyAddress.radioButton("#value_0")
      PropertyAddress.clickSubmitButton()
      Then("User is on the Review screen")
      PropertyAddress.clickSubmitButton()

      Then("User should be on Is the User and Individual Page")
      IsAnIndividualPage.verifyPageTitle(IsAnIndividualPage.pageTitle)
    }

    Scenario("Hit the TaskList with a return id") {
      Given("I enter login using the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, Some("123456"))

      Then("I should be on the initial page")
      InitialPage.verifyPageTitle(InitialPage.pageTitle)
      InitialPage.verifyTaskRowStatus("status-prelimQuestionDetailRow", "Complete")
      Then("User clicks Prelim Questions Task")
      InitialPage.clickLinkById("task-list-link-prelim-questions")

      Given("User is on the Individual or Company Name page")
      IndividualOrCompanyPage.verifyPageTitle(IndividualOrCompanyPage.pageTitle)
      Then("User input their name or company name and submits")
      IndividualOrCompanyPage.input(By.id("purchaserSurnameOrCompanyName"), "Test Name")
      IndividualOrCompanyPage.clickSubmitButton()

      Given("User is on the Address Look-up screen")
      PropertyAddress.verifyPageTitle(PropertyAddress.pageTitle)
      When("User clicks on the link")
      PropertyAddress.clickAddressManually()
      And ("User enters the address manually")
      PropertyAddress.enterAddressManually("123", "ABC", "XYZ123")
      PropertyAddress.clickSubmitButton()
      Then("User is on the Review screen")
      PropertyAddress.clickSubmitButton()

      Then("User should be on Is the User and Individual Page")
      IsAnIndividualPage.verifyPageTitle(IsAnIndividualPage.pageTitle)
    }
  }
}
