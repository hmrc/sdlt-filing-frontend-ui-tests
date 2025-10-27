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
import uk.gov.hmrc.ui.pages.{AboutTheTransactionPage, AuthWizard, IndividualOrCompanyPage, InitialPage, IsAnIndividualPage}
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
      Then("User should be on the Individual or Company Name page")
      IndividualOrCompanyPage.verifyPageTitle(IndividualOrCompanyPage.pageTitle)
      Then("User input their name or company name and submits")
      IndividualOrCompanyPage.input(By.id("purchaserSurnameOrCompanyName"), "Test Name")
      IndividualOrCompanyPage.clickSubmitButton()
      Then("User should be on Is the User and Individual Page")
      IsAnIndividualPage.verifyPageTitle(IsAnIndividualPage.pageTitle)
      Then("User should be navigated to the About the Transaction Page")
      // change this navigate to when the address lookup work has been done
      AboutTheTransactionPage.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/preliminary-questions/transaction-type"
      )
      AboutTheTransactionPage.verifyPageTitle(AboutTheTransactionPage.pageTitle)
      AboutTheTransactionPage.radioButton("#value_0")
      AboutTheTransactionPage.saveAndContinue()
    }

    Scenario("Hit the TaskList with a return id") {
      Given("I enter login using the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, Some("123456"))
      Then("I should be on the initial page")
      InitialPage.verifyPageTitle(InitialPage.pageTitle)
      InitialPage.verifyTaskRowStatus("status-prelimQuestionDetailRow", "Complete")
      Then("User clicks Prelim Questions Task")
      InitialPage.clickLinkById("task-list-link-prelim-questions")
      Then("User should be on the Individual or Company Name page")
      IndividualOrCompanyPage.verifyPageTitle(IndividualOrCompanyPage.pageTitle)
      Then("User input their name or company name and submits")
      IndividualOrCompanyPage.input(By.id("purchaserSurnameOrCompanyName"), "Test Name")
      IndividualOrCompanyPage.clickSubmitButton()
      Then("User should be on Is the User and Individual Page")
      IsAnIndividualPage.verifyPageTitle(IsAnIndividualPage.pageTitle)
      Then("User should be navigated to the About the Transaction Page")
      // change this navigate to when the address lookup work has been done
      AboutTheTransactionPage.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/preliminary-questions/transaction-type"
      )
      AboutTheTransactionPage.verifyPageTitle(AboutTheTransactionPage.pageTitle)
      AboutTheTransactionPage.radioButton("#value_0")
      AboutTheTransactionPage.saveAndContinue()
    }
  }
}
