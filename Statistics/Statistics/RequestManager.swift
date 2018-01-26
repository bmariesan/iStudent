//
//  RequestManager.swift
//  ExamTest
//
//  Created by Florin Ionita on 1/25/18.
//  Copyright © 2018 mobile. All rights reserved.
//

import UIKit
import Alamofire

struct Parser {
    static let teacherName = "teacherName"
    static let yearOfStudy = "yearOfStudy"
    static let startDate = "startDate"
    static let endDate = "endDate"
    static let completed = "completed"
    static let notCompleted = "notCompleted"
    static let name = "name"
    
    static let nr = "nr"
    static let total_attachments = "total_attachments"
    static let nr_max = "nr_max"
}

let baseUrl = "http://172.25.12.182:4001"
let coursesURL = "\(baseUrl)/courses"
let feedbackTeachersURL = "\(baseUrl)/feedback_teachers"
let feedbackStudentsURL = "\(baseUrl)/feedback_students"
let assignmentsFileAllURL = "\(baseUrl)/assignments_file_all"
let assignmentsFileMaxURL = "\(baseUrl)/assignments_file_max"
let assignmentsFileHaveURL = "\(baseUrl)/assignements_have_files"

class RequestManager: NSObject {

    var courses: [[String: Any]]!
    var feedbackTeachers: Int!
    var feedbackStudents: Int!
    var totalAttachments: Int!
    var maxAttachments: Int!
    var assigmentsThatHaveAttachements: Int!
    
    static private let shared = RequestManager.init()
    
    static func sharedInstance() -> RequestManager {
        return self.shared
    }
    
    private override init() {
        super.init()
        
        self.makeCoursesRequest()
        self.makeFeedbackStudentsRequest()
        self.makeFeedbackTeachersRequest()
        self.makeAllAssigmentsNumberRequest()
        self.makeMaxAttachementsNumberForFile()
        self.makeNrAssigmentsThatHaveFileRequest()
    }
    
    func makeCoursesRequest() {
        Alamofire.request(coursesURL, method: .get, parameters: nil, encoding: JSONEncoding.default, headers: nil).responseJSON { (response) in
            switch response.result {
            case .success(let json):
                if let data = json as? [[String: Any]] {
                    self.courses = data
                }
            case .failure: break
            }
        }
    }
    
    func makeFeedbackTeachersRequest() {
        Alamofire.request(feedbackTeachersURL, method: .get, parameters: nil, encoding: JSONEncoding.default, headers: nil).responseJSON { (response) in
            switch response.result {
            case .success(let json):
                if let data = json as? Int {
                    self.feedbackTeachers = data
                }
            case .failure: break
            }
        }
    }
    
    func makeFeedbackStudentsRequest() {
        Alamofire.request(feedbackStudentsURL, method: .get, parameters: nil, encoding: JSONEncoding.default, headers: nil).responseJSON { (response) in
            switch response.result {
            case .success(let json):
                if let data = json as? Int {
                    self.feedbackStudents = data
                }
            case .failure: break
            }
        }
    }
    
    func makeAllAssigmentsNumberRequest() {
        Alamofire.request(assignmentsFileAllURL, method: .get, parameters: nil, encoding: JSONEncoding.default, headers: nil).responseJSON { (response) in
            switch response.result {
            case .success(let json):
                if let data = json as? Int {
                    self.totalAttachments = data
                }
            case .failure: break
            }
        }
    }
    
    func makeMaxAttachementsNumberForFile() {
        Alamofire.request(assignmentsFileMaxURL, method: .get, parameters: nil, encoding: JSONEncoding.default, headers: nil).responseJSON { (response) in
            switch response.result {
            case .success(let json):
                if let data = json as? Int {
                    self.maxAttachments = data
                }
            case .failure: break
            }
        }
    }
    
    func makeNrAssigmentsThatHaveFileRequest() {
        Alamofire.request(assignmentsFileHaveURL, method: .get, parameters: nil, encoding: JSONEncoding.default, headers: nil).responseJSON { (response) in
            switch response.result {
            case .success(let json):
                if let data = json as? Int {
                    self.assigmentsThatHaveAttachements = data
                }
            case .failure: break
            }
        }
    }
    
}
