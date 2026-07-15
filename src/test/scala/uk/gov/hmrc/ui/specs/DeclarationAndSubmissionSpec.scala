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
import uk.gov.hmrc.ui.pages.DeclarationAndSubmission.*
import uk.gov.hmrc.ui.tags.*
import uk.gov.hmrc.ui.util.Users.LoginTypes.HASDIRECT
import uk.gov.hmrc.ui.util.Users.UserTypes.Organisation
import org.openqa.selenium.By
import uk.gov.hmrc.selenium.webdriver.Driver.instance

class DeclarationAndSubmissionSpec
    extends AnyFeatureSpec
    with BaseSpec
    with GivenWhenThen
    with ShouldVerb
    with BeforeAndAfterAll
    with BeforeAndAfterEach
    with Browser
    with ScreenshotOnFailure {

  Feature("SDLT Filing Frontend Declaration and Submission") {

    Scenario(
      "Complete the Declaration and Submission flow",
      DeclarationAndSubmissionJourney
    ) {

      Given("the user logs in through the Authority Wizard page")
      AuthWizard.login(
        HASDIRECT,
        Organisation,
        returnId = Some("submission-complete-multiples")
      )

      When("the user clicks on the 'Submit Your Return' link on ReturnTaskList")
      ReturnTaskList.clickLinkById("task-list-link-submit-your-return")
      Then("the user is navigated to the Declaration And Submission Before you start page")
      DeclarationAndSubmissionBeforeYouStart.verifyPageTitle(DeclarationAndSubmissionBeforeYouStart.pageTitle)

      // do next step once completed-return page created:

      /*
        When the user click on View and Print this return
        Then the user is navigated to the completed return form
       */

      When("the user clicks the Continue button")
      DeclarationAndSubmissionBeforeYouStart.saveAndContinue()
      Then("the AddEmailConfirmation page is shown")
      AddEmailConfirmation.verifyPageTitle(AddEmailConfirmation.pageTitle)

      When("the user chooses to receive an email confirmation when the return is submitted")
      AddEmailConfirmation.radioButton(AddEmailConfirmation.yes)
      AddEmailConfirmation.saveAndContinue()
      Then("the user is navigated to What email address should HMRC use?")
      EnterEmailAddress.verifyPageTitle(EnterEmailAddress.pageTitle)

      When("the user enters email address and clicks Save and Continue")
      EnterEmailAddress.input(By.id(EnterEmailAddress.emailAddress), EnterEmailAddress.emailAddressInput)
      EnterEmailAddress.saveAndContinue()
      Then("the user is navigated to Do you want certificate page")
      DoYouWantCertificate.verifyPageTitle(DoYouWantCertificate.pageTitle)

      When("the user selects yes and continues")
      DoYouWantCertificate.radioButton(DoYouWantCertificate.yes)
      DoYouWantCertificate.saveAndContinue()
      Then("the user is navigated to Who are you submitting this return for")
      WhoAreYouSubmittingThisReturnFor.verifyPageTitle(WhoAreYouSubmittingThisReturnFor.pageTitle)

      When("the user selects purchaserAuthorised and continues")
      WhoAreYouSubmittingThisReturnFor.radioButton(WhoAreYouSubmittingThisReturnFor.purchaserAuthorised)
      WhoAreYouSubmittingThisReturnFor.saveAndContinue()
      Then("the user is navigated to Confirm Declaration")
      DeclarationConfirmation.verifyPageTitle(DeclarationConfirmation.pageTitle)

      When("the user clicks Confirm and Submit")
      DeclarationConfirmation.saveAndContinue()

      And("then the user is navigated to SubmissionComplete page")
      SubmissionComplete.waitForPage()
      SubmissionComplete.verifyPageTitle(SubmissionComplete.pageTitle)

      When("the user click on views your submitted return")
      SubmissionComplete.click(SubmissionComplete.submittedReturnLink)
      Then("the user is navigated to completed SDLT return page")
      CompletedReturn.verifyPageTitle(CompletedReturn.submittedReturnPageTitle)

      When(
        "the user navigates back to the SubmissionComplete page and clicks on the 'View your submission receipt' link"
      )
      SubmissionComplete.navigateBackToPage()
      SubmissionComplete.verifyPageTitle(SubmissionComplete.pageTitle)
      SubmissionComplete.click(SubmissionComplete.sdlt5certificateLink)
      Then("the SubmissionReceipt page is shown")
      SubmissionReceipt.switchToNewTabAndValidateTitle(SubmissionReceipt.pageTitle)
    }
  }
}
