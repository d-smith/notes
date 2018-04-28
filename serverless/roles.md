# Using Pre-Defined Roles

Let's say you work somewhere where a dedicated team owns all IAM permissions and roles in your AWS account, and you want to use the 
serverless tools.

To do this, author the role ahead of time, then reference it in your serverless.yml

## Role Authoring

To author the roles, create a AWS::IAM::Role for the lambda functions and reference any managed policies that already exist. Then, create additional managed policies the role needs that don't exist already, referencing the role you are creating.

## Role Reference

In your serverless.yml, just reference the role via its ARN - see
the [serverless IAM reference](https://serverless.com/framework/docs/providers/aws/guide/iam/) for details.


## Example Role

<pre>
AWSTemplateFormatVersion: 2010-09-09
Parameters:
  Stage:
    Type: String
    Description: 'The application stage to which this role applies (dev, nonprod, prod,...)'
Resources:
  MySLSRuntimeRole:
    Type: 'AWS::IAM::Role'
    Properties:
      ManagedPolicyArns:
        - !Join 
          - ':'
          - - 'arn:aws:iam:'
            - !Ref 'AWS::AccountId'
            - policy/Pre_Defined_Policy_1
        - !Join 
          - ':'
          - - 'arn:aws:iam:'
            - !Ref 'AWS::AccountId'
            - policy/Pre_Defined_Policy_2
      RoleName: !Join 
        - ''
        - - MySLSRuntimeRole-
          - !Ref Stage
      AssumeRolePolicyDocument:
        Version: 2012-10-17
        Statement:
          - Effect: Allow
            Principal:
              Service: lambda.amazonaws.com
            Action:
              - 'sts:AssumeRole'

  PushRuntimeLogs:
    Type: 'AWS::IAM::ManagedPolicy'
    Properties:
      PolicyDocument:
        Version: 2012-10-17
        Statement:
          - Sid: logs
            Effect: Allow
            Action:
              - 'logs:PutLogEvents'
              - 'logs:CreateLogStream'
            Resource: !Join 
              - ''
              - - 'arn:aws:logs:'
                - !Ref 'AWS::Region'
                - ':'
                - !Ref 'AWS::AccountId'
                - ':'
                - 'log-group:/aws/lambda/*'
      Roles:
        - !Ref MySLSRuntimeRole

  PushRuntimeSNS:
    Type: 'AWS::IAM::ManagedPolicy'
    Properties:
      PolicyDocument:
        Version: 2012-10-17
        Statement:
          - Sid: logs
            Effect: Allow
            Action:
              - 'sns:Publish'
            Resource: '*'
      Roles:
        - !Ref MySLSRuntimeRole

  PushRuntimeSES:
    Type: 'AWS::IAM::ManagedPolicy'
    Properties:
      PolicyDocument:
        Version: 2012-10-17
        Statement:
          - Sid: logs
            Effect: Allow
            Action:
              - 'ses:SendEmail'
              - 'ses:VerifyEmailIdentity' 
            Resource: '*'
      Roles:
        - !Ref MySLSRuntimeRole
  

</pre>