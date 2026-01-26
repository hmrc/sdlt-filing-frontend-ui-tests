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
import uk.gov.hmrc.ui.pages.VendorAgent.VendorAgentsNamePage
import uk.gov.hmrc.ui.tags.*
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation

class VendorAgentSpec
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
      "Complete the Vendor Agent user journey with contact details and reference information",
      VendorAgentJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("no-vendor"))
      When("the user clicks on the 'Vendor Questions' link")
      AboutTheVendorPage.clickLinkById("task-list-link-vendor-agent-questions")
      Then("the user is navigated to the Vendor Agents Name page")
//     remove below step once the navigation is ready
      VendorAgentsNamePage.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/about-the-vendors-agent/agent-name"
      )
      VendorAgentsNamePage.verifyPageTitle(VendorAgentsNamePage.pageTitle)
      When("the user inputs their Vendor Agent's name")
      VendorAgentsNamePage.input(
        By.id(VendorAgentsNamePage.agentName),
        VendorAgentsNamePage.agentNameInput
      )
      And("clicks the Save and continue button")
      VendorAgentsNamePage.saveAndContinue()
    }

    Scenario(
      "Complete the Vendor Agent user journey without contact details and reference information",
      VendorAgentJourney
    ) {
      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(HASDIRECT, Organisation, returnId = Some("no-vendor"))
      When("the user clicks on the 'Vendor Questions' link")
      AboutTheVendorPage.clickLinkById("task-list-link-vendor-agent-questions")
      Then("the user is navigated to the Vendor Agents Name page")
//      remove below step once the navigation is ready
      VendorAgentsNamePage.navigateToPage(
        "http://localhost:10910/stamp-duty-land-tax-filing/about-the-vendors-agent/agent-name"
      )
      VendorAgentsNamePage.verifyPageTitle(VendorAgentsNamePage.pageTitle)
      When("the user inputs their Vendor Agent's name")
      VendorAgentsNamePage.input(
        By.id(VendorAgentsNamePage.agentName),
        VendorAgentsNamePage.agentNameInput
      )
      And("clicks the Save and continue button")
      VendorAgentsNamePage.saveAndContinue()
    }

  }
}
