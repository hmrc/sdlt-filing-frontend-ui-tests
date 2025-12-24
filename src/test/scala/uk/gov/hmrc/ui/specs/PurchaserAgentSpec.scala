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

import org.scalatest.featurespec.AnyFeatureSpec
import org.scalatest.verbs.ShouldVerb
import org.scalatest.{BeforeAndAfterAll, BeforeAndAfterEach, GivenWhenThen}
import uk.gov.hmrc.selenium.webdriver.{Browser, ScreenshotOnFailure}
import uk.gov.hmrc.ui.pages.*
import uk.gov.hmrc.ui.pages.PurchaserAgent.PurchaserAgentBeforeYouStartPage
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation
import org.scalatest.Tag
object PurchaserAgentJourney extends Tag("PurchaserAgentJourney")

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
    }
  }
}
